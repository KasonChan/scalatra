# Scalatra

This repo is created for practicing and understanding 
[Scalatra](http://www.scalatra.org/).

### Build & Run ###

```sh
$ cd WebApp
$ ./sbt
> container:start
> browse
> ~ ;copy-resources;aux-compile
```

If `browse` doesn't launch your browser, manually open 
[http://localhost:8080/](http://localhost:8080/) in your browser.

`~ ;copy-resources;aux-compile` is for automatic code reloading.

### References ###

- First project http://www.scalatra.org/2.4/getting-started/first-project.html
- Response code https://github.com/scalatra/scalatra/blob/develop/core/src/main/scala/org/scalatra/ActionResult.scala
