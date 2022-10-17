<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<style>
.fileZone{
width:300px;
height:200px;
box-sizing:border-box;
display:flex;
flex-wrap:wrap;
border:2px dotted #0b85a1;
align-content:flex-start;}
.fileMsg{
color:#0b85a1;
font-size:13px;
width:100%;
text-align:center;
align-self:center;}
.fileName{
width:100%;
position:relative;
heigh:18px;}

.fileName>span{
font-size:16px;
line-height:18px;}
.closeBtn{
position : absolute;
right:10px;
cursor:pointer}
</style>
<body>
<h1>게시글작성(파일추가)</h1>
	<form action = "/boardWrite.do" method="post" encType="multipart/form-data">
	<table border="1">
	<tr>
	<th>제목</th>
	<td><input type="text" name="boardTitle"></td>
	</tr>
	<tr>
	<th>첨부파일</th>
	<td><div class="fileZone">
		<div class="fileMsg">여기에 파일을 두세요</div>
	</div></td>
	</tr>
	<tr>
	<th>내용</th>
	<td><textarea  style="white-space:pre;" name="boardContent"></textarea></td>
	</tr>
	<tr>
	<th colspan="2">
	<input type="file" name ="boardFile" multiple style="display:none">
	<input type="hidden" value="${sessionScope.m.memberId }" name="boardWriter">
	<input type="submit" value="글쓰기">
	</th>
	</tr>
	</table>
	<a href="/">메인으로 돌아가기</a>
	</form>
	<script type="text/javascript">
		const fileZone = $(".fileZone");
		
		const files = new Array();
		fileZone.on("dragenter",function(e){
			e.stopPropagation();
			e.preventDefault();
			$(this).css("border","2px dashed #0b85a1")
		})
		
		fileZone.on("dragleave",function(e){
			e.stopPropagation();
			e.preventDefault();
			$(this).css("border","2px dotted #0b85a1")
		})
		
		fileZone.on("dragover",function(e){
			e.stopPropagation();
			e.preventDefault();
		})
		
		fileZone.on("drop",function(e){
			e.stopPropagation();
			e.preventDefault();
			
			
			for(let i=0;i<e.originalEvent.dataTransfer.files.length;i++){
				files.push(e.originalEvent.dataTransfer.files[i])
			};
			$(".fileMsg").hide();
			$(this).find(".fileName").remove();
			for(let i=0;i<files.length;i++){
				const fileNameDiv = $("<div>");
				fileNameDiv.addClass("fileName");
				const fileNameSpan =$("<span>");
				fileNameSpan.text(files[i].name);
				const closeBtn = $("<span>")
				closeBtn.addClass("closeBtn");
				closeBtn.text("X");
				closeBtn.attr("onclick","deleteFile(this)")
				fileNameDiv.append(fileNameSpan).append(closeBtn);
				$(this).append(fileNameDiv);
				
			}
			fileSetting();
		});
		
		function deleteFile(obj){
			const deleteFilename = $(obj).prev().text();
			for(let i=0;i<files.length;i++){
				console.log(files[i].name);
				console.log(deleteFilename);
				if(files[i].name == deleteFilename){
					files.splice(i,1);
					break;
				}
			}
			if(files.length == 0){
				$(".fileMsg").show();
				fileZone.css("border","2px dotted #0b85a1");
			}
			$(obj).parent().remove();
			fileSetting();
		}
		
		function fileSetting(){
			//input[type=file]변경용 객체 필요
			const dataTransfer = new DataTransfer();
			for(let i=0;i<files.length;i++){
				dataTransfer.items.add(files[i]);
			}
			$("input[name=boardFile]").prop("files",dataTransfer.files);
		}
	</script>
</body>
</html>