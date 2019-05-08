package bank;

class Account
    private int balance;
    private String name;
    private String phone_num;
    private String account_id;

    Account(String _name, String _phone_num, String _account_id){
        this.balance = 0;
        this.name = _name;
        this.phone_num = _phone_num;
        this.account_id = _account_id;
    }

    public String getAccountID(){
        return this.account_id;
    }
    
    public int getBalance(){
        return this.balance;
    }

    public void setBalance(int _balance){
        this.balance += _balance;
    }

}

