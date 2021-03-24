class CartV3 {
    constructor() {
        this.listItems = [];
        this.totalQuantity = 0;
        this.totalCost = 0;
    }

    clear() {
        this.listItems = [];
        this.recalculate();
    }

    add(product) {
        for (let i = 0; i < this.listItems.length; i++) {
            let oi = this.items[i];
            if (oi.productId === product.id) {
                oi.incQuantity();
                this.recalculate();
                return;
            }
        }
        this.listItems.push(new OrderItem(product));
        this.recalculate();
    }

    recalculate() {
        let cartCost = 0;
        let cartQuantity = 0;
        for (let i = 0; i < this.listItems.length; i++) {
            let oi = this.listItems[i];
            cartCost += oi.price;
            cartQuantity += oi.quantity;
        }
        this.totalCost = cartCost;
        this.totalQuantity = cartQuantity;
    }

    // надо ещё удаление продукта из корзины
}