package less9;

public class PermissionSet {

    private static int READ = 1 << 0;
    private static int WRITE = 1 << 1;
    private static int DELETE = 1 << 2;
    private static int COMMENT = 1 << 3;
    private static int EDIT_OTHERS = 1 << 4;
    private static int BAN = 1 << 5;
    private static int SEE_HIDDEN = 1 << 6;
    private static int ADMIN_SETTING = 1 << 7;

    private  int flags;

    public PermissionSet(){
        this.flags = 0;
    }

    public void grant(int premissionIndex){

        flags = flags +  premissionIndex;
        System.out.println(flags);
    }

    public void multigrant(int... premissionsIndexs){

        for (int iter : premissionsIndexs){
            flags = flags + iter;
            System.out.println(iter);

        }
        System.out.println(flags);

    }

    public void revoke(int premissionIndex){
        flags = flags - premissionIndex;

        System.out.println(flags);
    }

    public boolean hasDangerousCombination(){

        return true;
    }

    public boolean has(int premissionIndex){


        return true;
    }

    public String toPrettyString(){
        return "test";
    }



        public static void main(String[] args) {

            PermissionSet prem = new PermissionSet();

            prem.grant(WRITE);


            System.out.println(prem.has(WRITE));

        }




}
