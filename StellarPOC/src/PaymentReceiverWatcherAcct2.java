import org.stellar.sdk.Asset;
import org.stellar.sdk.AssetTypeCreditAlphaNum;
import org.stellar.sdk.AssetTypeNative;
import org.stellar.sdk.KeyPair;
import org.stellar.sdk.Network;
import org.stellar.sdk.Server;
import org.stellar.sdk.requests.EventListener;
import org.stellar.sdk.requests.PaymentsRequestBuilder;
import org.stellar.sdk.responses.operations.OperationResponse;
import org.stellar.sdk.responses.operations.PaymentOperationResponse;
 
public class PaymentReceiverWatcherAcct2 {
    public static String myToken = null;
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Network.useTestNetwork();
        Server server = new Server("https://horizon-testnet.stellar.org");
        KeyPair account = KeyPair.fromAccountId("GDDL2QXA7VXFU4MJS7C3PHXN5T5T3E6W65KZQDVDNEQHH5UYSH5HLUPO");
        PaymentsRequestBuilder paymentsRequest = server.payments().forAccount(account);
         
        String lastToken = loadLastPagingToken();
        if (lastToken != null) {
          paymentsRequest.cursor(lastToken);
        }
         
        paymentsRequest.stream(new EventListener<OperationResponse>() {
              @Override
              public void onEvent(OperationResponse payment) {
                // Record the paging token so we can start from here next time.
                savePagingToken(payment.getPagingToken());
 
                // The payments stream includes both sent and received payments. We only
                // want to process received payments here.
                if (payment instanceof PaymentOperationResponse) {
                  if (((PaymentOperationResponse) payment).getTo().equals(account)) {
                    return;
                  }
 
                  String amount = ((PaymentOperationResponse) payment).getAmount();
 
                  Asset asset = ((PaymentOperationResponse) payment).getAsset();
                  String assetName;
                  if (asset.equals(new AssetTypeNative())) {
                    assetName = "lumens";
                  } else {
                    StringBuilder assetNameBuilder = new StringBuilder();
                    assetNameBuilder.append(((AssetTypeCreditAlphaNum) asset).getCode());
                    assetNameBuilder.append(":");
                    assetNameBuilder.append(((AssetTypeCreditAlphaNum) asset).getIssuer().getAccountId());
                    assetName = assetNameBuilder.toString();
                  }
 
                  StringBuilder output = new StringBuilder();
                  output.append(amount);
                  output.append(" ");
                  output.append(assetName);
                  output.append(" from ");
                  output.append(((PaymentOperationResponse) payment).getFrom().getAccountId());
                  System.out.println(output.toString());
                }
 
              }
            });
 
    }
    protected static void savePagingToken(String pagingToken) {
        // TODO Auto-generated method stub
        myToken = pagingToken;
        System.out.println(String.format("myToken is %s",myToken));
    }
    private static String loadLastPagingToken() {
        // TODO Auto-generated method stub
        return myToken;
    }
 
}