$(function() {
    $(".main_menu a:nth-child(2)").addClass("active");
    $("#add_category").click(function(){ 
        $(".popup_wrap").addClass("open");
        $("#add_cate").css("display", "inline-block");
        $("#modify_cate").css("display", "none");
        $(".popup .top_area h2").html("카테고리 추가")
        $(".popup .top_area p").html("카테고리 정보를 입력해주세요")
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

            $("#cate_name").val("");
            $("#cate_code").val("");

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

        let modify_data_seq = 0;

        $(".modify_btn").click(function(){
            modify_data_seq = $(this).attr("data-seq");
            $(".popup_wrap").addClass("open");
            $("#add_cate").css("display", "none");
            $("#modify_cate").css("display", "inline-block");
            $(".popup .top_area h2").html("카테고리 수정")
            $(".popup .top_area p").html("수정 정보를 입력해주세요")
            $.ajax({
                type:"get",
                url:"/category/get?seq="+$(this).attr("data-seq"),
                success:function(r) {
                    console.log(r);
                    $("#cate_name").val(r.data.cate_name);
                    $("#cate_code").val(r.data.cate_code);
                }
            })
        })

        $("#modify_cate").click(function(){
            if(confirm("수정하시겠습니까?") == false) return;
            let cate_name = $("#cate_name").val();
            let cate_code= $("#cate_code").val();

            let data = {
                cate_seq:modify_data_seq,
                cate_name:cate_name,
                cate_code:cate_code,
            }
            $.ajax({
                type:"patch",
                url:"/category/update",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        })

        $("#search_btn").click(function(){
            location.href="/category?keyword="+$("#keyword").val();
        })
        $("#keyword").keydown(function(e){
            console.log(e.keyCode)
            if(e.keyCode == 13) {
                $("#search_btn").trigger("click");
            }
        })
    })
