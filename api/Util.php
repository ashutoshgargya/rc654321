<?php

class Util {

    public static function generateRandomString( $length = 6 ) {
        $chars   = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
        $len     = strlen( $chars );
        $randStr = '';
        for ( $i = 0; $i < $length; $i++ ) {
            $randStr .= $chars[rand(0, $len - 1)];
        }
        return $randStr;
    }

}