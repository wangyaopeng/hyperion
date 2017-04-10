# hyperion is a stream pipeline framework

## example
* define handlers
  ```
      static class Handler1 implements Handler<RequestType, ResponseType> {
          @Override
          public void handleRequest(HandlerContext context, ContextHolder<RequestType, ResponseType> contextHolder) {
               //example: get amount from request
              long flag = contextHolder.getRequest().getAmount();
              if (flag < 30) {//success, pass request to next handler
                  context.write(contextHolder);
              } else if (flag < 60) {// return directly
                  ResponseType response = new ResponseType();
                  contextHolder.getResponse().setSuccess(response);
              } else {// throw exception
                  contextHolder.getResponse().setFailure(new IllegalArgumentException("amount cannot over 60"));
              }
          }
      }
      
      static class Handler2 implements Handler<RequestType, ResponseType> {
          @Override
          public void handleRequest(HandlerContext context, ContextHolder<RequestType, ResponseType> contextHolder) {
              context.write(contextHolder);
              //set success
              long flag = contextHolder.getRequest().getAmount() + 10000;
              ResponseType<Long> response = new ResponseType<Long>().setSuccess(true).setData(flag);
              contextHolder.getResponse().setSuccess(response);
          }
      }
  ```

* construct handler pipeline, use LinkedList, the last handler added will be executed first 
    ```
    StreamPipeline pipeline = new StreamPipeline()
                    .addFirst(new Handler2()).addFirst(new Handler1());
    ```
* execute request by pipeline
    ```
    OrderRequest request = new OrderRequest().setAmount(index * 15);
    ContextHolder<OrderRequest, OrderResponse> holder = new ContextHolder<>(request);
    pipeline.request(holder);
    ```
* get response from ContextHolder
    ```
    holder.getResponse().getNow() // return immediately whatever request is executed completely
    holder.getResponse().get() // thread wait until request be executed completely
    holder.getResponse().get(long timeout, TimeUnit timeUnit)// thread wait period of time until request be executed completely, if time out, throw exception TimeOutException
    ```