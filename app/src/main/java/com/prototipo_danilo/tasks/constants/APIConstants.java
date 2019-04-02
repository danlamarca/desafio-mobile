package com.prototipo_danilo.tasks.constants;

public class APIConstants {

    //CLASSE DE REQUISIÇÕES:

    //header das requisiçoes:
    public static class HEADER {
        public static final String TOKEN_KEY="(Headers: “Content-Type : application/json”)";
    }

    public static class ENDPOINT {
        //URL´s para exemplo
        public static final String ROOT="https://desafio.mobfiq.com.br/Search/Criteria";
    }

    public static class OPERATION_METHOD {
        public static final String GET= "GET";
        public static final String PUT="PUT";
        public static final String DELETE ="DELETE";
        public static final String POST="POST";
    }

    public static class PARAMETERS {
        public static final String ContentType= "Content-Type";
        public static final String Query= "Query";
        public static final String Offset= "Offset";
        public static final String Size= "Size";
    }

    public static class STATUS_CODE {
        public static final int SUCCESS=200;
        public static final int FORBIDDEN=403;
        public static final int NOT_FOUND=404;
        public static final int INTERNAL_SERVER_ERROR=500;
        public static final int INTERNET_NOT_AVAILABLE=901;
    }

}
