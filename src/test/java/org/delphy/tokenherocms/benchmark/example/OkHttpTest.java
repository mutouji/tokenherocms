package org.delphy.tokenherocms.benchmark.example;

public class OkHttpTest {
    // http://www.vogella.com/tutorials/JavaLibrary-OkHttp/article.html
// 1. To use OkHttp you need to create a Request object.
//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("http://www.vogella.com/index.html")
//            .build();

//    2. You can also add parameters
//    HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.github.help").newBuilder();
//urlBuilder.addQueryParameter("v", "1.0");
//urlBuilder.addQueryParameter("user", "vogella");
//    String url = urlBuilder.build().toString();
//
//    Request request = new Request.Builder()
//            .url(url)
//            .build();

//    3. You can also add authentication headers
//    Request request = new Request.Builder()
//            .header("Authorization", "your token")
//            .url("https://api.github.com/users/vogella")
//            .build();

//   1.3 Sending and receiving network calls
//      1. To make a synchronous network call, use the Client to create a Call object and use the execute method.
//
//          Response response = client.newCall(request).execute();

//       2. To make asynchronous calls, also create a Call object but use the enqueue method.
//
//        client.newCall(request).enqueue(new Callback() {
//        @Override
//        public void onFailure(Call call, IOException e) {
//            e.printStackTrace();
//        }
//
//        @Override
//        public void onResponse(Call call, final Response response) throws IOException {
//            if (!response.isSuccessful()) {
//                throw new IOException("Unexpected code " + response);
//            } else {
//                // do something wih the result
//            }
//        }
}
