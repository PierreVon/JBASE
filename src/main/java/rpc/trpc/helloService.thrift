service  MyHelloService {
  string sayHello(1:string username)
}
// thrift -gen java [file]