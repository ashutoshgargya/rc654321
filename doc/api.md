API Calls
=========

Insert a User: API call to register/insert a user

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

======

Request Invite Code: API call to request an invite code

`GET /api/api.php?action=requestInviteCode`

The server will always respond with a 200.

Example:

    $ curl -iX GET https://www.revelcare.com/api/api.php?action=requestInviteCode

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "code": "REVELNOW",
    }

======

Validate Invite Code: API call to make sure an invite code is valid

`GET /api/api.php?action=validateInviteCode&code=<code>`

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
