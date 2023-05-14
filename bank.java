import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
public class bank {
    public static void main(String[] args){
        boolean register = false;

        ArrayList<HashMap<String, String>> users = new ArrayList<>(); //이렇게 해야 계좌개설할 때마다 덮어쓰는 오류를 방지할 수 있다.

       while(!register) {
        System.out.println("=======Bank Menu=======");
        System.out.println("1.계좌개설");
        System.out.println("2. 입금하기");
        System.out.println("3. 출금하기");
        System.out.println("4. 전체조회");
        System.out.println("5. 계좌이체");
        System.out.println("6. 프로그램 종료");
        System.out.println("========================");

        Scanner sc = new Scanner(System.in);


        System.out.print("입력 : ");
        int number = sc.nextInt();

        if (number == 1){
            Scanner sc1 = new Scanner(System.in);
            Scanner sc2 = new Scanner(System.in);

            HashMap<String, String> user = new HashMap<>();

            while (true){
                //계좌번호
                System.out.println("계좌개설을 진행합니다.");
                System.out.println("========================");
                System.out.print("사용하실 계좌번호를 입력해주십시오 : ");
                String account_number = sc1.nextLine();
                System.out.print("계좌번호 확인 : ");
                String account_number_confirm = sc2.nextLine();


                if ((account_number).equals(account_number_confirm)) {
                    //이름
                    System.out.print("이름을 입력해주십시오 : ");
                    String name = sc1.nextLine();


                    //최초 예금
                    System.out.print("입금하실 예금을 입력해주십시오 : ");
                    String deposit = sc2.nextLine();

                    //Map 관리
                    user.put("name", name);
                    user.put("account_number", account_number);
                    user.put("deposit", deposit);

                    users.add(user);


                    //계좌개설 완료
                    System.out.println("============================");
                    System.out.printf("계좌 개설이 완료되었습니다.\n");
                    System.out.printf("계좌번호 : %s\n", account_number);
                    System.out.printf("이름 : %s\n", name);
                    System.out.printf("현재금액 : %s원\n", deposit);
                    System.out.println("###============================###");
                    break;


                } else {
                    System.out.println("계좌번호가 일치하지 않습니다. 처음부터 다시 입력해 주세요.");
                }}
            }

        else if (number == 2) {
            Scanner sc3 = new Scanner(System.in);

            System.out.println("입금을 진행합니다.");
            System.out.println("============================");

            System.out.print("입금하실 계좌번호를 입력해주세요 : ");
            String account_number = sc3.nextLine();


            //Map에서 계좌번호와 일치하는 사용자 찾기
            boolean register2 = false;
            for (int i = 0; i < users.size(); i++) {
                    if (((HashMap) users.get(i)).get("account_number").equals(account_number)) {

                        System.out.print("입금하실 금액을 입력해주세요 : ");
                        String deposit = sc3.nextLine();

                        //계좌번호와 일치하는 사용자의 잔액에 입금액을 추가
                        int newBalance = Integer.parseInt((String) ((HashMap) users.get(i)).get("deposit")) + Integer.parseInt(deposit);
                        String newBalance_deposit =Integer.toString(newBalance);
                        ((HashMap) users.get(i)).replace("deposit", newBalance_deposit);


                        System.out.println("입금이 완료되었습니다.");
                        System.out.print("현재금액 : ");
                        System.out.print(((HashMap) users.get(i)).get("deposit"));
                        System.out.print("원\n");
                        register2=true;
                        break;
                    }
                }
                if (!register2) {
                    System.out.println("해당되는 계좌번호가 없습니다. 처음부터 다시 진행해 주세요.");
                }
            }

        else if (number == 3){
            Scanner sc4 = new Scanner(System.in);

            System.out.println("출금을 진행합니다.");
            System.out.println("============================");

            System.out.print("출금하실 계좌번호를 입력해주세요 : ");
            String account_number = sc4.nextLine();


            //Map에서 계좌번호와 일치하는 사용자 찾기
            boolean register3 = false;
            for (int i = 0; i < users.size(); i++) {
                if (((HashMap) users.get(i)).get("account_number").equals(account_number)) {

                    System.out.print("출금하실 금액을 입력해주세요 : ");
                    String withdraw = sc4.nextLine();

                    //계좌번호와 일치하는 사용자의 잔액에 입금액을 추가
                    int newBalance = Integer.parseInt((String) ((HashMap) users.get(i)).get("deposit")) - Integer.parseInt(withdraw);
                    String newBalance_withdraw =Integer.toString(newBalance);
                    ((HashMap) users.get(i)).replace("deposit", newBalance_withdraw);

                    System.out.println("출금이 완료되었습니다.");
                    System.out.print("현재금액 : ");
                    System.out.print(((HashMap) users.get(i)).get("deposit"));
                    System.out.print("원\n");
                    register3=true;
                    break;
                }
            }
            if (!register3) {
                System.out.println("해당되는 계좌번호가 없습니다. 처음부터 다시 진행해 주세요.");
            }
        }

        else if (number == 4) {
            Scanner sc5 = new Scanner(System.in);

            System.out.println("전체조회를 진행합니다.");
            System.out.println("============================");

            System.out.print("조회하실 계좌번호를 입력해주세요 : ");
            String account_number = sc5.nextLine();

            //Map에서 계좌번호와 일치하는 사용자 찾기
            boolean register4 = false;
            for (int i = 0; i < users.size(); i++) {
                if (((HashMap) users.get(i)).get("account_number").equals(account_number)) {

                    //이름 조회
                    System.out.print("이름 : ");
                    System.out.println(((HashMap) users.get(i)).get("name"));

                    //현재금액 조회
                    System.out.print("현재금액 : ");
                    System.out.print(((HashMap) users.get(i)).get("deposit"));
                    System.out.print("원\n");
                    register4 = true;
                    break;
                }
            }
            if (!register4) {
                System.out.println("해당되는 계좌번호가 없습니다. 처음부터 다시 진행해 주세요.");
            }
        }

        else if (number == 5) {
            boolean register5 = false;
            boolean register6 = false;
            Scanner sc6 = new Scanner(System.in);
            Scanner sc7 = new Scanner(System.in);
            Scanner sc8 = new Scanner(System.in);

            System.out.println("계좌이체를 진행합니다.");
            System.out.println("============================");

            System.out.print("출금하실 계좌번호를 입력해주세요 : ");
            String account_number_you = sc6.nextLine();

            //Map에서 출금자(본인) 계좌번호와 일치하는 사용자 찾기
            for (int i = 0; i < users.size(); i++) {
                if (((HashMap) users.get(i)).get("account_number").equals(account_number_you)) {
                    System.out.print("입금하실 계좌번호를 입력해주세요 : ");
                    String account_number_opp = sc7.nextLine();

                    //Map에서 입금자(상대방) 계좌번호와 일치하는 사용자 찾기
                    for (int j = 0; j < users.size(); j++) {
                        if (((HashMap) users.get(j)).get("account_number").equals(account_number_opp)) {
                            System.out.print("입금하실 금액을 입력해주세요 : ");
                            String deposit_opp = sc8.nextLine();

                            //상대방 계좌에 입금
                            int newBalance1 = Integer.parseInt((String) ((HashMap) users.get(j)).get("deposit")) + Integer.parseInt(deposit_opp);
                            String oppcurrent = Integer.toString(newBalance1);
                            ((HashMap) users.get(j)).replace("deposit", oppcurrent);

                            //내 계좌에 출금
                            int newBalance2 = Integer.parseInt((String) ((HashMap) users.get(i)).get("deposit")) - Integer.parseInt(deposit_opp);
                            String mycurrent = Integer.toString(newBalance2);
                            ((HashMap) users.get(i)).replace("deposit", mycurrent);

                            System.out.println("계좌이체가 완료되었습니다.");

                            //본인 계좌 현재상태
                            System.out.print("내계좌 현재금액 : ");
                            System.out.print(((HashMap) users.get(i)).get("deposit"));
                            System.out.print("원\n");

                            //상대방 계좌 현재상태
                            System.out.print("상대계좌 현재금액 : ");
                            System.out.print(((HashMap) users.get(j)).get("deposit"));
                            System.out.print("원\n");
                            register6 = true;
                            break;
                        }
                    }
                    if (!register6) {
                        System.out.println("해당되는 계좌번호가 없습니다. 처음부터 다시 진행해 주세요.");
                    }

                    register5 = true;
                    break;
                }
            }
            if (!register5) {
                System.out.println("해당되는 계좌번호가 없습니다. 처음부터 다시 진행해 주세요.");
            }
        }



        else if (number == 6){
            System.out.println("========================");
            System.out.println("프로그램을 종료합니다.");
            System.out.println("========================");

            register=true;
        }

       }
    }
}
