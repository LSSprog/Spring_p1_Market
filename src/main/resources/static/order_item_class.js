class OrderItem {
    constructor(product) {
        this.productId = product.id;
        this.productTitle = product.title;
        this.quantity = 1;
        this.pricePerProduct = product.price;
        this.cost = this.quantity * this.pricePerProduct;
    }

    incQuantity() {
        this.quantity++;
        this.cost = this.quantity * this.pricePerProduct;
    }

    decQuantity() {
        if (this.quantity > 0) {
        this.quantity--;
        this.price = this.quantity * this.pricePerProduct;
        }
    }
}