# japi-gateway
A simple API Gateway written in Java. Add APIs, monitor the access, manage cache times, etc...

### Database
The database that stores the WebServices is an embedded H2.
When grabbing the project from scratch, be sure to launch the `main()` method from InitDbDAO (it initializes the database).
The database is composed of a main table, `webservices`, whose structure is:
---------------------------------------------------------------------
|   id_ws   |   label   |   url_from  |     url_to    |     cache_time_in_ms    |
---------------------------------------------------------------------

+ `label`: The name of the web service. Whatever you want.
+ `url_from`: is the URL that arrives in this application, for instance: https://myapi.mysite.com/api1/what?id=1 then put `/api1/what` in this field
+ `url_to`: is the URL that leaves this application, for instance: if "api1/what?id=1" has to be mapped to "https://secret.endpoint.com:18080" then put `secret.endpoint.com:18080` in this field
+ `cache_time_in_ms`: the time you want to cache the response from the `url_to` URL. This cache is managed by this application, so if you have a cache of 3600ms (1 minute)
    , and call `/api1/what?id=1` twice in a minute (be it the same client or not), only one call will be made to "https://secret.endpoint.com:18080"
    On the contrary, if the first time you call `api1/what?id=1`, and the second time you call `api1/what?id=2`, then 2 calls will be made to "https://secret.endpoint.com:18080"
