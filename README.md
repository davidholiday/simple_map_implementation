# simple typed map 

has the following features:

* CRUD operations 
* create and update operations will auto-convert to their opposite if appropriate (ie - an add operation against a key that already exists will auto-convert to an update operation)
* map KV objects are statically typed
* CRUD operations are recorded internally and can be retrieved 