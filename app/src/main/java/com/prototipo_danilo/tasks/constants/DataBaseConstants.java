package com.prototipo_danilo.tasks.constants;

/**
 * Todas as constantes utilizadas no banco de dados
 * Tabelas, Colunas
 */
public class DataBaseConstants {

    /**
     * Não deve ser instanciada
     */
    private DataBaseConstants() {
    }

    /**
     * Tabelas disponíveis no banco de dados com suas colunas
     */
    public static class PRODUCTS {
        public static final String TABLE_NAME = "produtos";

        public static class COLUMNS {
            public static final String ID = "_id";//id do produto
            public static final String DESCRICAO = "descricao";
            public static final String NOME="nome";
            public static final String REALID="realid";
        }
    }

    public static class IMAGENS {
        public static final String TABLE_NAME = "imagens";

        public static class COLUMNS {
            public static final String ID = "_id";
            public static final String URL = "url";
            public static final String TAG="tag";
            public static final String REALIDPROD="realidprod";//id do produto
        }
    }

    public static class SELLERS {
        public static final String TABLE_NAME = "sellerst";

        public static class COLUMNS {
            public static final String ID = "_id";
            public static final String PRICE = "price"; //preco tabela
            public static final String LASTPRICE = "lastprice";//preco final
            public static final String REALIDPROD="realidprod";//id do produto
        }
    }

    public static class BESTINSTALLMENT {
        public static final String TABLE_NAME = "bestinstalmment";

        public static class COLUMNS {
            public static final String ID = "_id";
            public static final String PARCELAMENTO = "parcelamento";//parcelamento
            public static final String TOTAL="total";//total parcelamento
            public static final String VALOR="valor";//parcelado
            public static final String DESCONTO="desconto"; //desconto
            public static final String REALIDPROD="realidprod";//id do produto
        }
    }

}
