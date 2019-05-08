package bank;

class Bank{
    private String name;
    private int country_code;
    private Account [] account_list;
    
    Bank(String _name, int _country_code, Account [] list){
        this.account_list = list;
        this.name = _name;
        this.country_code = _country_code;
    }

    public int Check(String account_id){
        int account_index = 0;
        
        for(int i = 0; i < this.account_list.length; i++){
            if(this.account_list[i].getAccountID() == account_id){
                account_index = i;
                break;
            }
        }
        return this.account_list[account_index].getBalance();
    }

    public void Deposit(String account_id, int deposit){
        int account_index = 0;
        
        for(int i = 0; i < this.account_list.length; i++){
            if(this.account_list[i].getAccountID() == account_id){
                account_index = i;
                break;
            }
        }
        this.account_list[account_index].setBalance(deposit);
    }
}

