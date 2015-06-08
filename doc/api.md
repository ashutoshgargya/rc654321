API Calls
=========

Insert a User
-------------

`POST /api/api.php?action=insertUser`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 email_address | String | Email address of user 
 password | String | Alphanumeric password 

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 email_address | String | Email address of user 
 other_field | Unknown | Any other fields (except password) 

Response (Failure - http code 403):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 error | Bool | true 
 message | String | Descriptive message for display 

Example:

    $ curl --data "email_address=bingo1@gmail.com&password=secret" https://www.revelcare.com/api/api.php?action=insertUser

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "email_address": "bingo1@gmail.com",
    }


Authenticate a User
-------------------

`POST /api/api.php?action=authUser`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 email_address | String | Email address of user 
 password | String | Alphanumeric password 

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 email_address | String | Email address of user 
 other_field | Unknown | Any other fields (except password) 

Response (Failure - http code 403):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 error | Bool | true 
 message | String | Descriptive message for display 

Example:

    $ curl --data "email_address=bingo1@gmail.com&password=secret" https://www.revelcare.com/api/api.php?action=authUser

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "email_address": "bingo1@gmail.com",
    }

Save Patient Details
--------------------

`POST /api/api.php?action=updateUser`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 field1 | Unknown | Field in details
 field2 | Unknown | Field in patient details

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 email_address | String | Email address of user 
 field1 | Unknown | Other field (except password) 
 field2 | Unknown | Other field (except password) 

Response (Failure - http code 403):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 error | Bool | true 
 message | String | Descriptive message for display 

Example:

    $ curl --data "id=55599c61af1dd17d038b4567&name=Test+User1&dob=01/01/1980&allergies=peanuts" https://www.revelcare.com/api/api.php?action=updateUser

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "email_address": "bingo1@gmail.com",
        "name": "Test User1",
        "dob": "01/01/1980",
        "allergies": "Peanuts",
        "field1": "field1 data here",
        "field2": "field2 data here",
    }

Save Insurance Details
----------------------

`POST /api/api.php?action=updateInsuranceDetails`

Request: (this needs to be a multi-part MIME request)

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 filedata | binary blob | Uploaded file (image of insurance card)

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 email_address | String | Email address of user 
 insurance_fileid | String | Unique fileid of uploaded file
 field1 | Unknown | Other field (except password) 
 field2 | Unknown | Other field (except password) 

Response (Failure - http code 403):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 error | Bool | true 
 message | String | Descriptive message for display 

Example:

    $ curl -F filedata=@insurance_card.png -F "id=55645a99af1dd127448b4567"  https://www.revelcare.com/api/api.php?action=updateInsuranceDetails

    {
        "_id": { "$id": "55645a99af1dd127448b4567" },
        "email_address": "bingo1@gmail.com",
        "name": "Test User1",
        "dob": "01/01/1980",
        "insurance_fileid": "5566520baf1dd1c7448b456a",
    }

Insert a Contact for a User
---------------------------

`POST /api/api.php?action=insertContact`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 contact | String | User Contact + Details

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 other_field | Unknown | Any other fields (except password) 

Example:

    $ curl --data "id=555f57beaf1dd129448b4568&contact=Dr+Scooby+Doo,4086661212" https://www.revelcare.com/api/api.php?action=insertContact

    {
        "_id": {"$id":"555f57beaf1dd129448b4568"},
        "email_address":"abc@gmailsef.com",
        "full_name":"adwa wa",
        "phone_number":"1112221111",
        "dob": "Tue Jan 01 1980 00:00:00 GMT-0800 (PST)",
        "contacts":["Dr Doogie Howser,4085551111","Dr Scooby Doo,4086661212"]
    }

Get Contacts for a User
-----------------------

`POST /api/api.php?action=getContacts`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 

Response (Success - http code 200): (list of the following objects)

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of user 
 other_field | Unknown | Any other fields (except password) 

Example:

    $ curl --data "id=555f57beaf1dd129448b4568" https://www.revelcare.com/api/api.php?action=getContacts

    [
        "Dr Doogie Howser,4085551111",
        "Dr Scooby Doo,4086661212"
    ]

Insert a Pickup for a User
-------------------------

`POST /api/api.php?action=insertPickup`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 userid | String | Unique ID of user 
 pickup_window | String | Delivery window
 other_data | Unknown | Any other fields

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of pickup
 userid | String | Unique ID of user
 pickup_window | String | Delivery window specified
 other_field | Unknown | Any other fields

Example:

    $ curl --data "userid=556c4791af1dd1c9448b456c&pickup_window=12:00-3:00&other_field1=some+data+here" https://www.revelcare.com/api/api.php?action=insertPickup

    {
        "_id": {"$id": "556cd73aaf1dd1c5448b4570" },
        "userid": "556c4791af1dd1c9448b456c",
        "pickup_window": "12:00-3:00",
        "other_field1": "some data here",
    }

Get Pickups for a User
----------------------

`POST /api/api.php?action=getPickups`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 userid | String | Unique ID of user 

Response (Success - http code 200): (list of the following objects)

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of pickup
 userid | String | Unique ID of user
 pickup_window | String | Delivery window specified
 other_field | Unknown | Any other fields

Example:

    $ curl --data "userid=556c4791af1dd1c9448b456c" https://www.revelcare.com/api/api.php?action=getPickups

    [
        {
            "_id": { "$id": "556cd375af1dd1c7448b4574" },
            "pickup_window": "12:00-3:00",
            "userid": "556c4791af1dd1c9448b456c"
        },
        {
            "_id": { "$id": "556cd6d7af1dd1c9448b456e" },
            "pickup_window": "3:00-6:00",
            "userid": "556c4791af1dd1c9448b456c",
        }
    ]

Request Invite Code
-------------------

`GET /api/api.php?action=requestInviteCode`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of invite code 
 code | String | Invite code 

Example:

    $ curl -iX GET https://www.revelcare.com/api/api.php?action=requestInviteCode

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "code": "REVELNOW",
    }


Validate Invite Code
--------------------

`GET /api/api.php?action=validateInviteCode&code=<code>`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---
 code | String | Invite code to validate 

Response (Success - http code 200):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 id | String | Unique ID of invite code 
 code | String | Invite code 

Response (Failure - http code 403):

 *Field* | *Type* | *Description* 
 --- | --- | ---
 error | Bool | true 
 message | String | Descriptive message for display 


The server will always respond with a 200.

Example:

    $ curl -iX GET https://www.revelcare.com/api/api.php?action=validateInviteCode&code=REVELNOW

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "code": "REVELNOW",
    }


    $ curl -iX GET https://www.revelcare.com/api/api.php?action=validateInviteCode&code=JUNK

    {
        "error": true,
        "message": "Code not exist",
    }

Get Pharmacy List
-----------------

`GET /api/api.php?action=pharmacyList`

Request:

 *Field* | *Type* | *Description* 
 --- | --- | ---

Response (Success - http code 200): (list of following objects)

 *Field* | *Type* | *Description* 
 --- | --- | ---
 _id | Object | { "$id": "Unique ID for Pharmacy" }
 name | String | Pharmacy Name
 description | String | Pharmacy description
 address | String | Pharmacy Address
 phone | String | Pharmacy Phone

Example:

    $ curl -iX GET https://www.revelcare.com/api/api.php?action=pharmacyList

    [
       {
           "_id": { "$id": "55599c61af1dd17d038b4567" },
           "name": "CVS",
           "description": "CVS Cupertino on De Anza Blvd",
           "address": "10455 S De Anza Blvd Cupertino, CA 95014",
           "phone": "4089961911",
       },
       {
           "_id": { "$id": "555a220aaf1dd1440a8b4567" },
           "name": "CVS",
           "description": "CVS Sunnyvale on El Camino Real",
           "address": "576 E El Camino Real Sunnyvale, CA 94087",
           "phone": "4087394033",
       },
    ]



