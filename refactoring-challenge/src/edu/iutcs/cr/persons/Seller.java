package edu.iutcs.cr.persons;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Seller extends Person {

    public Seller(String name, String id, String email) {
        super(name, id, email);
    }

    public Seller(String id) {
        super(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}