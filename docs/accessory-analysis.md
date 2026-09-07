**1. Should accessories be integrated into the existing product hierarchy**

**(extending `Product`) or form an independent hierarchy?**

**Justify your decision considering code reusability and model consistency.**



They can extend `Product` to reuse the product constructor—which includes the attributes `id`, `title`, `price`, `stock`, `type`, and `compatibility`—as well as the `getDescription` method.



**2. What attributes are common to the three types of accessories, and which are**

**specific to each type? How is this distinction reflected in the module's**

**class hierarchy?**



The common attributes are identifier, title, price, and stock. Regarding specific attributes: controllers have connection type and compatibility; cables are characterized by length and connector type; and memory units by capacity and type.



**3. Compatibility between an accessory and a console is a relationship between two**

**system entities. How is this relationship represented in the design and in**

**persistence? Is compatibility an attribute of the accessory, the console,**

**or both?**



It is a one-to-one relationship (an association); the accessory requires compatibility with the console to function. Compatibility is an attribute of both, as the values ​​must match.



**4. What modifications are needed in the sales service class (`SaleService`)**

**so that sales can include accessories without breaking existing**

**behavior regarding video games and consoles?**



We must validate that the product—in this case, the accessory—is available or in stock. Since it inherits from `Product`, a product object is used to verify that all items in the sale are in stock.



**5. In which layer of the system architecture should the new accessory module**

**classes be placed? Justify your decision based on the**

**responsibilities of each layer.**



They should be placed in the model layer, as this layer represents the system's data and the basic rules governing domain objects.



