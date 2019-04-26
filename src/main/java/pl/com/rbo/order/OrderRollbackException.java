package pl.com.rbo.order;

public class OrderRollbackException extends Exception {

    public OrderRollbackException(){
        super("Can not rollback order");
    }

}
