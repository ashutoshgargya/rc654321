API Calls
=========

`GET /api.php?action=requestInviteCode`

The server will always respond with a 200.

Example:

    $ curl -iX GET https://www.revelcare.com/api/api.php?action=requestInviteCode

    {
        "_id": { "$id": "55599c61af1dd17d038b4567" },
        "code": "REVELNOW",
    }

--------

`GET /api.php?action=validateInviteCode&code=<code>`

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
