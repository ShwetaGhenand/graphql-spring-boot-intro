###### Application Run Commad:
```
1. mvn clean
2. mvn install
3. mvn install -DskipTests
4. mvn spring-boot:run
```

##### Queries
###### 1. CREATE ITEM QUERY:
```
mutation createItem($input:CreateItemDto!){
  createItem(input:$input){
    id
    itemName
    itemBrand
    itemCategory
    productDetails{
      id
      productQuantity
      productDescription
    }
   
  }
}
```

###### Query Variable:
```
{
  "input": {
    "productDetailsId": 1,
    "itemName": "S-N-01",
    "itemBrand": "NIKE",
    "itemCategory": "SPORT SHOES"
  }
}
```

###### 2. GET ALL ITEMS:
```
query getItems {
  items{
    id
    itemName
    itemBrand
    itemCategory
    productDetails{
      id
      productQuantity
      productDescription
    }
  } 
}
```

###### 3. GET ITEM BY ID:
```
query getItem($id:Long!) {
  item(id:$id){
  id
    itemName
    itemBrand
    itemCategory
    productDetails{
      id
      productQuantity
      productDescription
    }
  } 
}
```
###### Query Variable

```
{
  "id": 1
}
```

###### 4. UPDATE ITEM DETAILS:
```
mutation updateItem($input:UpdateItemDto!){
  updateItem(input:$input){
    id
    itemName
    itemBrand
    itemCategory
   
  }
}
```
###### Query Variable
``` 
{
  "input": {
    "id": 1,
    "itemName": "S-N-01",
    "itemBrand": "ADIDAS",
    "itemCategory": "SPORT SHOES"
  }
}
```

###### 5. DELETE ITEM:
``` 
mutation deleteItem($id:Long!) {
  deleteItem(id:$id)
}

```
###### Query Variable
```
{
  "id": 1
}
```

###### 6. CREATE PRODUCT DETAILS:
``` 
mutation createProductDetails($input:CreateProductDetailsDto!){
  createProductDetails(input:$input){
    id
   productQuantity
    productDescription
  }
}
```
###### Query Variable
```
{
  "input": {
    "productQuantity": "2",
    "productDescription": "test descrription"
  }
}
```

###### 7. GET ALL PRODUCT DETAILS:
```
query getProductDetails {
  productDetails{
  id
    productQuantity
    productDescription
  }  
}
```

###### 8. GET PRODUCT BY ID:
```
query getProductDetail ($id:Long!) {
  productDetail(id:$id){
    id
    productQuantity
    productDescription
  }
}

```