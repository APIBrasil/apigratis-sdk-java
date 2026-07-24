package com.apibrasil.sdk.core.transport;

import com.apibrasil.sdk.core.Json;
import com.apibrasil.sdk.core.ResponseType;

import java.nio.charset.StandardCharsets;

/**
 * Decodifica o corpo da resposta conforme o {@link ResponseType} pedido.
 *
 * <p>Em {@link ResponseType#JSON} o corpo é lido sempre como UTF-8 (o gateway
 * nem sempre declara o charset, e o padrão da plataforma estragaria os
 * acentos) e, se não for JSON válido, volta como texto.
 */
public final class BodyDecoder {

    private BodyDecoder() {
    }

    /** Decodifica os bytes recebidos. */
    public static Object decode(byte[] bytes, ResponseType responseType) {
        if (responseType == ResponseType.BYTES) {
            return bytes == null ? new byte[0] : bytes;
        }
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String text = new String(bytes, StandardCharsets.UTF_8);
        if (text.isBlank()) {
            return null;
        }
        return Json.decode(text);
    }
}
