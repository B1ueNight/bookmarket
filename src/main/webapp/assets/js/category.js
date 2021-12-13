$(function() {
    $(".main_menu a:nth-child(2)").addClass("active");
    $("#add_category").click(function(){ 
        // alert("학과 추가 팝업 열기");
        $(".popup_wrap").addClass("open");
    })
        $("#add_cate").click(function(){
            if(confirm("카테고리를 등록하시겠습니까?") == false) return;
            let cate_name = $("#cate_name").val();
            let cate_code = $("#cate_code").val();

            let data = {
                cate_name:cate_name,
                cate_code:cate_code,
            }
        $.ajax({
            type:"post",
            url:"/category/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status)
                    location.reload();
            }
        })
    })
        $("#cancel_cate").click(function(){
            if(confirm("취소하시겠습니까? \n(입력된 정보는 저장되지 않습니다.)") == false) return;

            $("#ca_name").val("");
            $("#ca_code").val("");

            $(".popup_wrap").removeClass("open");
        })

        $(".delete_btn").click(function(){
            if(confirm("카테고리를 삭제하시겠습니까? \n(이 동작은 되돌릴 수 없습니다)") == false) return;

            let seq = $(this).attr("data-seq");
            $.ajax({
                type:"delete",
                url:"/category/delete?seq="+seq,
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        });

        $(".modify_btn").click(function() {
            if(confirm("카테고리를 수정하시겠습니까? \n(수정 후 되돌릴 수 없습니다.)") == false return;)

            $.ajax({
                type:"patch",
                url:"/category/modify?seq="+seq,
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        })

        $("#cancel_modify").click(function(){
            if(confirm("취소하시겠습니까? \n(입력된 정보는 저장되지 않습니다.)") == false) return;

            $("#ca_name").val("");
            $("#ca_code").val("");

            $(".popup_wrap").removeClass("open");
        })
})
