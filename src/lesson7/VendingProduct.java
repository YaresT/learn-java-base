package lesson7;

enum VendingProduct {

    SNEAKERS(10, 1, "Кроссовки"),
    ELEPHANT(20, 2, "Слон"),
    HAT(30, 3, "Шляпа"),
    COW(40, 4, "Корова");

    private final int price;

    private final int code;

    private final String name;

    VendingProduct(int price, int code, String name) {
        this.price = price;
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }
}

