# SYSC4806_MiniShopify

## Project Topic

Merchant can create a new shop by filling in a form containing: the name of the shop, the categories/tags relevant to the shop, and other fields that are up to you. The Merchant can upload products to populate the shop along with a description, picture, and inventory number. Customers can find a shop by looking up the name of the shop or searching by category/tag. Once they find the shop the want, they can browse through the product catalog of the shop, and can then decide to purchase one or many products by putting them in the Shopping Cart and proceeding to Checkout. The purchase itself will obviously be simulated, but purchases cannot exceed the inventory.

## Setup Instructions
Can access H2 Console at http://localhost:8080/h2-console. The database will save to the path /data/MiniShopify. It can be altered by changing the field spring.datasource.url=jdbc:h2:file:/data/MiniShopify in the application.properties files. If tables need to be precreated create a data.sql file in main/java/resources and Spring Boot will do it automatically.  
