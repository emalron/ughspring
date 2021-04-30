<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
    <div class="row-md-10 my-1">
        <form method="post" action="${contextPath }/board/write.do" encType="UTF-8">
        <input class="col" type="text" name="title" placeholder="제목을 입력하세요" />
        <div class="my-1">
            <textarea name="content" id="editor" rows="10" cols="80"></textarea>
        </div>
        <div class="my-1 d-flex justify-content-end">
            <input type="submit" class="btn btn-primary mr-2" value="작성" />
            <input type="reset" class="btn btn-secondary" value="다시 작성" />
        </div>
    </div>
</form>
<script src="${contextPath}/ckeditor/ckeditor.js"></script>
<script>
    ClassicEditor
        .create( document.querySelector( 'textarea#editor' ), {
            ckfinder: {
                uploadUrl: '/pro30/board/image.do'
            },
            mediaEmbed: { previewsInData: true },
            toolbar: {
                items: [
                    'heading',
                    '|',
                    'bold',
                    'italic',
                    'link',
                    'bulletedList',
                    'numberedList',
                    '|',
                    'alignment',
                    'fontColor',
                    'fontSize',
                    'highlight',
                    '|',
                    'outdent',
                    'indent',
                    '|',
                    'imageUpload',
                    'blockQuote',
                    'insertTable',
                    'mediaEmbed',
                    'undo',
                    'redo'
                ]
            },
            language: 'en',
            image: {
                toolbar: [
                    'imageTextAlternative',
                    'imageStyle:full',
                    'imageStyle:side'
                ]
            },
            table: {
                contentToolbar: [
                    'tableColumn',
                    'tableRow',
                    'mergeTableCells'
                ]
            },
            licenseKey: '',
        } )
        .then( editor => {
            window.editor = editor;
        } )
        .catch( error => {
            console.error( error );
        } );
</script>