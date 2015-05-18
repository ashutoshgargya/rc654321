<?php

require_once ( dirname(__FILE__).'/LogUtil.php' );

main();

function main() {
    $m        = new MongoClient( "mongodb://localhost" );
    $db       = $m->revelcare;
    $input    = array_merge( $_POST, $_GET );
    $action   = $input['action'];

    LogUtil::logObj( "INPUT: ", $input );
    switch ( $action ) {
    case 'requestInviteCode':
        $res = requestInviteCode( $db, $input );
        break;
    case 'validateInviteCode':
        $res = validateInviteCode( $db, $input );
        break;
    case 'pharmacyList':
        $res = pharmacyList( $db, $input );
        break;
    default:
        $res = [ 'error' => true, 'message' => 'Unknown API call' ];
        break;
    }

    /*
    $id       = new MongoId( $input['id'] );
    $cursor   = $session->find( array( '_id' => $id ));
    $obj      = array();
    foreach ($cursor as $doc) {
        $obj    = $doc;
        $events = json_decode( $obj['events'] );
        $obj['events'] = $events;
    }
    */
    header( 'Access-Control-Allow-Origin: *' );
    echo( json_encode( $res ));
}

function requestInviteCode( $db, $input ) {
    $invite_code = $db->invite_code;
    $cursor      = $invite_code->find();
    $res         = [ 'error' => true, 'message' => 'No valid codes created' ];
    foreach ($cursor as $doc) {
        $res    = $doc;
    }
    return $res;
}

function validateInviteCode( $db, $input ) {
    $invite_code = $db->invite_code;
    $code        = $input['code'];
    $res         = [ 'error' => true, 'message' => 'Code not exist' ];
    if ( $code ) {
        $cursor  = $invite_code->find( [ 'code' => $code ] );
        foreach ($cursor as $doc) {
            $res = $doc;
        }
    }
    return $res;
}

function pharmacyList( $db, $input ) {
    $pharmacy    = $db->pharmacy;
    $res         = [];
    $cursor      = $pharmacy->find();
    foreach ($cursor as $doc) {
        $res[]   = $doc;
    }
    if ( count( $res ) <= 0 ) {
        $res     = [ 'error' => true, 'message' => 'No pharmacies found' ];
    }
    return $res;
}
