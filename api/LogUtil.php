<?php

class LogUtil {

    private static $fp_logger;
  
    private static function init( $logfile = 'php://stderr') {
        GLOBAL $fp_logger;
        if ( ! $fp_logger ) { $fp_logger = fopen( $logfile, 'a'); }
    }

    public static function log( $data, $backtraceLvl = 0 ) {
        GLOBAL $fp_logger;
        LogUtil::init();
        $details = LogUtil::generateLogDetails( $backtraceLvl + 1 );
        fputs ( $fp_logger, "$details: $data\n" );
    }

    public static function logObj( $data, $dataObj, $backtraceLvl = 0 ) {
        LogUtil::log( $data . var_export( $dataObj, true ), $backtraceLvl + 1 );
    }

    public static function generateLogDetails( $backtraceLvl = 0 ) {
        $caller = debug_backtrace();
        $detailFunction = '';
        $details = '';
        if( isset($caller[$backtraceLvl+1]) ) {
            $detailFunction = $caller[$backtraceLvl+1]['function'] . "()";
        }
        if ( isset($caller[$backtraceLvl]) ) {
            $f1 = strrpos( $caller[$backtraceLvl]['file'], '/' ) + 1;
            $f2 = strrpos( $caller[$backtraceLvl]['file'], '.' );
            $detailFile = substr( $caller[$backtraceLvl]['file'], $f1, $f2-$f1 );
            $details = "[" . $detailFile . "|" . $detailFunction . "|L" . $caller[$backtraceLvl]['line'] . "]";
        }
        $now = date( 'M/d/Y H:i:s' );
        return "[$now] [$detailFile|$detailFunction|L{$caller[$backtraceLvl]['line']}]";
    }

}