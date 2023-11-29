package tools;

import Main.CustomersList;
import Main.ProductList;
import java.util.Scanner;

public class Util {

    public static String inputString(String inputName, String condition) {
        String result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(" + condition + "): ");
            result = sc.nextLine().trim();
            if ("".equals(result)) {
                System.out.println("Your " + inputName + " is empty. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputAllowEmpty(String inputName, boolean allowEmpty) {
        String result;
        do {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter " + inputName + ": ");
            result = sc.nextLine().trim();
        } while (!allowEmpty && result.isEmpty());
        return result;
    }

    public interface CheckUnique {

        public boolean check(String checkedId);
    }

    public static String inputId(String inputName, String condition, CheckUnique checkUnique) {
        String result;
        while (true) {
            result = inputString(inputName, condition);
            if (!checkUnique.check(result)) {
                System.out.println("Your " + inputName + " is not unique. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputIdWithFormat(String inputName, String condition, CheckUnique checkUnique, String format) {
        String result;
        while (true) {
            result = Util.inputId(inputName, condition, checkUnique);
            if (!result.matches(format)) {
                System.out.println("Your " + inputName + " is wrong format. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputIntegerNumber(String inputName, String condition, boolean allowEmpty) throws OrdersException {
        String result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputName + "(" + condition + "): ");
            result = sc.nextLine().trim();
            boolean checkStringInt = Check.checkStringInt(result);
            if (!checkStringInt && !allowEmpty) {
                System.out.println("Please enter number!");
            } else {
                if ("".equals(result) && !allowEmpty) {
                    System.out.println("Your " + inputName + " is empty. Try again!");
                    continue;
                }
                if (!result.isEmpty()) {
                    boolean checkInteger = Check.checkIntegerNumber(result);
                    if (checkInteger) {
                        return result;
                    } else {
                        System.out.println("Your " + inputName + " can not less than or equal 0");
                        continue;
                    }
                }
                return result;
            }
        }
    }

    public static String inputUser(String inputname) {
        String result;
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter " + inputname + ": ");
            result = sc.nextLine().trim();
            if ("".equals(result)) {
                System.out.println("Your " + inputname + " is empty. Try again!");
                continue;
            }
            return result;
        }
    }

    public static String inputDate(boolean allowEmpty) {
        while (true) {
            System.out.println("Enter Order date(xx/xx/20xx): ");
            Scanner sc = new Scanner(System.in);
            String date = sc.nextLine();
            if (!allowEmpty && !date.isEmpty()) {
                boolean checkDate = Check.checkDate(date);
                if (checkDate) {
                    return date;
                } else {
                    System.out.println("The choice is not format");
                    continue;
                }
            }
            return date;
        }
    }

    public static String inputStatus(boolean allowEmpty) {
        while (true) {
            System.out.println("Enter Status (T or F): ");
            Scanner sc = new Scanner(System.in);
            String status = sc.nextLine();
            if (!allowEmpty && !status.isEmpty()) {
                boolean checkStatus = Check.checkStatus(status);
                if (checkStatus) {
                    return "true";
                }
                return "false";
            }
            return status;
        }
    }

    public static String inputCustomerID(String inputname, String condition, boolean allowEmpty) {
        do {
            System.out.println("Enter " + inputname + "(" + condition + "): ");
            Scanner sc = new Scanner(System.in);
            String newcusId = sc.nextLine();
            if (!allowEmpty) {
                if (newcusId.isEmpty()) {
                    System.out.println("Customer ID can not empty");
                    continue;
                } else {
                    return CustomersList.checkCusID(newcusId);
                }
            }
            return newcusId;
        } while (true);

    }

    public static String inputProductID(String inputname, String condition, boolean allowEmpty) {
        do {
            System.out.println("Enter " + inputname + "(" + condition + "): ");
            Scanner sc = new Scanner(System.in);
            String newproId = sc.nextLine();
            if (!allowEmpty) {
                if (newproId.isEmpty()) {
                    System.out.println("Product ID can not empty");
                    continue;
                } else {
                    String result = ProductList.checkProID(newproId);
                    if (result.equals("notAvaiable")) {
                        System.out.println("Customer is not avaiable. Please enter again!");
                        continue;
                    }
                    return result;
                }
            }
            return newproId;
        } while (true);
    }

    public static Integer inputInteger(String message, Integer minValue, Integer maxValue) {
        Integer inputVal = null;
        do {
            try {
                inputVal = Integer.valueOf(Util.inputString(message, false));
            } catch (NumberFormatException ex) {
                inputVal = null;
            }
        } while (inputVal == null
                || (minValue != null && minValue.compareTo(inputVal) > 0)
                || maxValue != null && maxValue.compareTo(inputVal) < 0);
        return inputVal;
    }

    public static String inputString(String message, boolean allowEmpty) {
        Scanner sc = new Scanner(System.in);
        String str = "";
        do {
            System.out.print(message + ": ");
            str = sc.nextLine();
        } while (!allowEmpty && str.isEmpty());
        return str.trim();
    }

}
