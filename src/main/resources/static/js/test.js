const url = new URL('http://vncodelab.com/lab/3?');
var arr = url.pathname.split("/");
console.log(arr[arr.length - 1])

console.log(url.searchParams.get('room'))
