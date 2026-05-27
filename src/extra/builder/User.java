package extra.builder;

public class User {
    private final String name;
    private final String email;
    private String address;
    private String phone;
    private String country;
    private boolean newsletter;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
        this.phone = builder.phone;
        this.country = builder.country;
        this.newsletter = builder.newsletter;
    }

    public static class Builder {
        private final String name;
        private final String email;
        private String address = "";
        private String phone = "";
        private String country = "DK";
        private boolean newsletter = false;

        public Builder(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public Builder address(String address) { this.address = address; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }
        public Builder country(String country) { this.country = country; return this; }
        public Builder newsletter(boolean newsletter) { this.newsletter = newsletter; return this; }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", country='" + country + '\'' +
                ", newsletter=" + newsletter +
                '}';
    }

}
