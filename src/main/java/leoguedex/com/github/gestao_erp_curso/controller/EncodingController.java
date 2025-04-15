package leoguedex.com.github.gestao_erp_curso.controller;

import com.fasterxml.jackson.databind.ser.Serializers.Base;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/encoding")
public class EncodingController {

  @PostMapping("/base64")
  public ResponseEntity<Map<String, String>> testBase64(@RequestBody String texto) {
    String codified = Base64.getEncoder().encodeToString(texto.getBytes(StandardCharsets.UTF_8));

    String resolved = new String(Base64.getDecoder().decode(codified), StandardCharsets.UTF_8);

    Map<String, String> result = new HashMap<>();
    result.put("Texto Codificado", codified);
    result.put("Texto Decodificado via Codigo", resolved);
    result.put("Texto Original", texto);

    return ResponseEntity.ok(result);
  }

  @PostMapping("/md5")
  public ResponseEntity<Map<String, String>> testMd5(@RequestBody String texto) throws NoSuchAlgorithmException {
    Map<String, String> result = new HashMap<>();
    result.put("Texto Original", texto);

    /* Codificacao */
    String md5Hash = codifyMd5(texto);
    result.put("hash md5", md5Hash);

    /* Verificacao */
    String newHash = codifyMd5(texto);
    boolean resultEqualsHashs = newHash.equals(md5Hash);
    result.put("Texto Corresponde ao Hash", Boolean.toString(resultEqualsHashs));

    return ResponseEntity.ok(result);
  }

  private String codifyMd5(String texto) throws NoSuchAlgorithmException {
    MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(texto.getBytes());
    byte[] digestedResult = md.digest();

    StringBuilder sb = new StringBuilder();
    for (byte b : digestedResult) {
      sb.append(String.format("%02x", b & 0xff));
    }

    return sb.toString();
  }

  @PostMapping("/basic-auth")
  public ResponseEntity<Map<String, String>> testBasicAuth(@RequestParam String login, @RequestParam String senha) {
    Map<String, String> result = new HashMap<>();

    /* Codificando */
    String startEncode = login + ":" + senha;
    String basicAuth = "Basic " + Base64.getEncoder().encodeToString(startEncode.getBytes(StandardCharsets.UTF_8));
    result.put("Basic Gerado", basicAuth);

    /* Decodificando */
    String codeAuth64 = basicAuth.substring("Basic ".length());
    String basicDecoded = new String(Base64.getDecoder().decode(codeAuth64), StandardCharsets.UTF_8);
    result.put("Basic Decodificado", basicDecoded);

    result.put("Login", login);
    result.put("Senha", senha);

    return ResponseEntity.ok(result);
  }

  @PostMapping("/basic-auth-header")
  public ResponseEntity<Map<String, String>> testBasicAuthHeader(@RequestHeader("Authorization") String headerBasic) {

    if (!headerBasic.startsWith("Basic ")) {
      return ResponseEntity.badRequest().body(Map.of("erro", "Header nao contem 'Basic '"));
    }

    Map<String, String> result = new HashMap<>();

    String headerSemBasic = headerBasic.substring("Basic ".length());
    String basicDecoded = new String(Base64.getDecoder().decode(headerSemBasic), StandardCharsets.UTF_8);
    result.put("Basic Decoded", basicDecoded);

    String[] partes = basicDecoded.split(":", 2);
    String usuario = partes[0];
    String senha = partes.length > 1 ? partes[1] : "";

    result.put("Header Recebido", headerBasic);
    result.put("Usuário", usuario);
    result.put("Senha", senha);

    return ResponseEntity.ok(result);
  }
}
