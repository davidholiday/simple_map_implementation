# simple typed map 

has the following features:

* supports CRUD operations 
* create and update operations will auto-convert to their opposite if appropriate (ie - an add operation against a key that already exists will auto-convert to an update operation)
* map KV objects are statically typed
* CUD operations are recorded internally and can be retrieved 
* map will report existence of a given kv pair