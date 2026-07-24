package com.apibrasil.sdk.core;

/** Como o corpo da resposta deve ser decodificado. */
public enum ResponseType {
    /** Decodifica JSON (padrão). Corpos não-JSON voltam como {@link String}. */
    JSON,

    /** Devolve os bytes crus — use para PDFs, imagens etc. */
    BYTES
}
