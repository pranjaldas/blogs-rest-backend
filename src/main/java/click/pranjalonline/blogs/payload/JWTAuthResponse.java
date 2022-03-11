package click.pranjalonline.blogs.payload;

public class JWTAuthResponse {
    private String accessToken;
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public JWTAuthResponse() {
    }

    public JWTAuthResponse(String accessToken, String tokenType) {
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }
}
