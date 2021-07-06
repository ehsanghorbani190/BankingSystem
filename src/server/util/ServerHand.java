package server.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import server.types.Account;
import server.types.Transaction;
import server.types.User;
import util.DataDealer;

public class ServerHand implements Runnable {
    private Socket socket;
    User user;

    public ServerHand(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
            while (true) {
                DataDealer request = (DataDealer) (in.readObject());
                if (request != null) {
                    DataDealer response = new DataDealer(-1);
                    switch (request.getStatus()) {
                        case 0: // NOTE 0 for LOGIN
                            user = User.login(request.getData("melliCode"), request.getData("password"));
                            if (user == null) {
                                response.setStatus(500);
                                response.setError(
                                        "Invalid Credentials: Password is wrong or no user found with this MelliCode");
                            } else {
                                response.setStatus(200);
                            }
                            break;
                        case 1:// NOTE 1 for SIGN-UP
                            if (request.getData("melliCode") == null || request.getData("name") == null
                                    || request.getData("password") == null || request.getData("email") == null
                                    || request.getData("phone") == null) {
                                response.setStatus(501);
                                response.setError("Invalid Credentials");
                            } else {
                                response.setStatus(201);
                                user = new User(request.getData("melliCode"), request.getData("name"),
                                        request.getData("password"), request.getData("email"),
                                        request.getData("phone"));
                            }
                            break;
                        case 2:// NOTE 2 for Getting accounts
                            if (user.getAccounts().size() == 0) {
                                response.setStatus(502);
                                response.setError("No Account found");
                            } else {
                                ArrayList<Account> accounts = user.getAccounts();
                                for (int i = 0; i < accounts.size(); i++)
                                    response.addData(String.valueOf(i), accounts.get(i).getUniqueID());
                                response.setStatus(202);
                            }
                            break;
                        case 3:// NOTE 3 for Getting Account
                            if (request.getData("id") == null) {
                                response.setStatus(5030);
                                response.setError("Provide an ID");
                            } else {
                                Account account = user.getAccount(response.getData("id"));
                                if (account == null) {
                                    response.setStatus(5031);
                                    response.setError("no account found with this id");
                                } else {
                                    response.setStatus(203);
                                    response.addData("alias", account.getAlias());
                                    response.addData("balance", String.valueOf(account.getBalance()));
                                }
                            }
                            break;
                        case 4:// NOTE 4 for Getting FAVORITE accounts
                            ArrayList<Account> fAccounts = user.getFavoriteAccounts();
                            if (fAccounts.size() == 0) {
                                response.setStatus(504);
                                response.setError("No Favorite Account found");
                            } else {
                                for (int i = 0; i < fAccounts.size(); i++)
                                    response.addData(String.valueOf(i), fAccounts.get(i).getUniqueID());
                                response.setStatus(202);
                            }
                            break;
                        case 5:// NOTE 5 for Getting Transactions of an account
                            if (request.getData("id") == null) {
                                response.setStatus(5050);
                                response.setError("Provide an ID");
                            } else {
                                Account account = user.getAccount(response.getData("id"));
                                if (account == null) {
                                    response.setStatus(5051);
                                    response.setError("no account found with this id");
                                } else {
                                    ArrayList<Transaction> trs = account.getTransactions();
                                    if (trs.size() == 0) {
                                        response.setStatus(5052);
                                        response.setError("No Transaction for this account");
                                    } else {
                                        response.setStatus(205);
                                        for (int i = 0; i < trs.size(); i++) {
                                            response.addData(i + "i", trs.get(i).getUniqueID());
                                            response.addData(i + "v", Long.toString(trs.get(i).getVal()));
                                            response.addData(i + "t", trs.get(i).getType());
                                            response.addData(i + "d", trs.get(i).getDate());
                                        }
                                    }
                                }
                            }
                            break;
                        case 6:// NOTE 6 for Opening account
                            if (request.getData("password") == null) {
                                response.setError("Password is required");
                                response.setStatus(506);
                            } else {
                                user.openAccount(request.getData("password"),
                                        Boolean.parseBoolean(request.getData("fav")), request.getData("alias"));
                                response.setStatus(206);
                            }
                            break;
                        case 7:// NOTE 7 for add alias to an account
                            if (request.getData("id") == null) {
                                response.setError("Provide an ID");
                                response.setStatus(5070);
                            } else {
                                Account account = user.getAccount(request.getData("id"));
                                if (account == null) {
                                    response.setStatus(5071);
                                    response.setError("No account found with provided ID");
                                } else {
                                    response.setStatus(207);
                                    account.setAlias(request.getData("alias"));
                                    account.setFavorite(true);
                                }
                            }
                            break;
                        case 8:// NOTE 8 for transfer money
                            if (request.getData("id") == null || request.getData("password") == null) {
                                response.setError("Invalid Credentials");
                                response.setStatus(5080);
                            } else {
                                boolean i = user.transfer(request.getData("id"),
                                        request.getData("password"), request.getData("dest"),
                                        Long.parseLong(request.getData("value")));
                                if (i) {
                                    response.setStatus(208);
                                } else {
                                    response.setStatus(5081);
                                    response.setError("Error while transfering money");
                                }
                            }
                            break;
                        case 9:// NOTE 9 for Bill
                            if (request.getData("id") == null) {
                                response.setError("Provide an ID");
                                response.setStatus(5090);
                            } else {
                                boolean i = user.payBill(request.getData("id"),
                                        request.getData("code"), request.getData("paycode"));
                                if (i) {
                                    response.setStatus(209);
                                } else {
                                    response.setStatus(5091);
                                    response.setError("Error while paying bill : Invalid Credentials ");
                                }
                            }
                            break;
                        case 10:// NOTE 10 for Loan
                            if (request.getData("id") == null || request.getData("value") == null || request.getData("period") == null) {
                                response.setError("Invalid Credentials");
                                response.setStatus(5010);
                            } else {
                                user.getLoan(request.getData("id"),
                                        Long.parseLong(request.getData("value")),
                                        Integer.parseInt(request.getData("period")));
                                response.setStatus(2010);
                            }
                            break;
                        case 11:// NOTE 11 for Closing Account
                            if (request.getData("id") == null || request.getData("password") == null
                                    || (user.getAccount(request.getData("id")).getBalance() != 0
                                            && request.getData("dest") == null)) {
                                response.setError("Invalid Credentials");
                                response.setStatus(5011);
                            } else {
                                user.deleteAccount(request.getData("id"), request.getData("password"),
                                        request.getData("dest"));
                                response.setStatus(2011);
                            }
                            break;
                        case 12:// NOTE 12 for deposit-withdraw
                            if (request.getData("id") == null) {
                                response.setError("Provide an ID");
                                response.setStatus(5012);
                            } else {
                                String type = request.getData("type");
                                boolean i;
                                if (type.equals("w")) {
                                    i = user.withdraw(request.getData("id"),
                                            Long.parseLong(request.getData("value")));
                                } else if (type.equals("d")) {
                                    i = user.deposit(request.getData("id"),
                                            Long.parseLong(request.getData("value")));
                                } else
                                    i = false;
                                if (i) {
                                    response.setStatus(2012);
                                } else {
                                    response.setStatus(50121);
                                    response.setError("Error while doing the job");
                                }
                            }
                            break;
                        default:
                            response.setStatus(5001);
                            response.setError("No method found");
                            break;
                    }
                    out.writeObject(response);
                    out.flush();
                    System.out.println(response.getError());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null)
                    out.close();
                if (in != null) {
                    in.close();
                    socket.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
