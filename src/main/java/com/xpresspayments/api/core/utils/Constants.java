package com.xpresspayments.api.core.utils;

public class Constants {
    public static final String AUTHORIZATION = "Authorization";

    public static final String BILLER_SUCCESS_CODE = "00";

    public static final String BILLER_SUCCESS_MESSAGE = "Successful";

    public static class  NetworkProviderUniqueCodes {
        public static final String MTN = "MTN_24207";
        public static final String AIRTEL = "MTN_24207";
        public static final String GLO = "MTN_24207";
        public static final String ETISALAT = "MTN_24207";
    }
    public static class BillerServiceEndpoints {
        public static final String PURCHASE_AIRTIME = "https://billerstest.xpresspayments.com:9603/api/v1";
    }
}
