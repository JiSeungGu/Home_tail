<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">

@import url(https://fonts.googleapis.com/css?family=Open+Sans);

.bodyXX{
  font-family:'Open Sans', sans-serif;
  width:90%;
  margin:0 auto;
  padding:2em 0 6em;
}

.wrapper{
  margin:3em 0;
}
.a,.a:visited,.a:hover,.a:active{
  -webkit-backface-visibility:hidden;
          backface-visibility:hidden;
	position:relative;
  transition:0.5s color ease;
	text-decoration:none;
	color:#81b3d2;
	font-size:2.5em;
}
.a:hover{
	color:#d73444;
}
.a.before:before,.a.after:after{
  content: "";
  transition:0.5s all ease;
  -webkit-backface-visibility:hidden;
          backface-visibility:hidden;
  position:absolute;
}
.a.before:before{
  top:-0.25em;
}
.a.after:after{
  bottom:-0.25em;
}
.a.before:before,.a.after:after{
  height:5px;
  height:0.35rem;
  width:0;
  background:#d73444;
}
.a.first:after{
  left:0;
}
.a.second:after{
  right:0;
}
.a.third:after,.a.sixth:before,.a.sixth:after{
  left:50%;
  -webkit-transform:translateX(-50%);
          transform:translateX(-50%);
}
.a.fourth:before,.a.fourth:after{
  left:0;
}
.a.fifth:before,.a.fifth:after{
  right:0;
}
.a.seventh:before{
  right:0;
}
.a.seventh:after{
  left:0;
}
.a.eigth:before{
  left:0;
}
.a.eigth:after{
  right:0;
}
.a.before:hover:before,.a.after:hover:after{
  width:100%;
}
.square{
  box-sizing:border-box;
  margin-left:-0.4em;
  position:relative;
  font-size:1.5em;
  overflow:hidden;
}
.square .a{
  position:static;
  font-size:100%;
  padding:0.2em 0.4em;
}
.square:before,.square:after{
  content: "";
  box-sizing:border-box;
  transition:0.25s all ease;
  -webkit-backface-visibility:hidden;
          backface-visibility:hidden;
  position:absolute;
  width:5px;
  width:0.35rem;
  height:0;
  background:#d73444;
}
.square:before{
  left:0;
  bottom:-0.2em;
}
.square.individual:before{
  transition-delay:0.6s;
}
.square:after{
  right:0;
  top:-0.2em;
}
.square.individual:after{
  transition-delay:0.2s;
}
.square .a:before{
  left:0;
  transition:0.25s all ease;
}
.square .a:after{
  right:0;
  transition:0.25s all ease;
}
.square.individual .a:after{
  transition:0.25s all ease 0.4s;
}
.square:hover:before,.square:hover:after{
  height:calc(100% + 0.4em);
}
.square:hover .a:before,.square:hover .a:after{
  width:100%;
}
</style>
</head>
<body>

<div class="bodyXX">
<div class="wrapper">
  <span class="square">
    <a class="a tenth before after" href="#">Cool Square Simultaneous</a>
  </span>
</div>
</div>

</body>
</html>