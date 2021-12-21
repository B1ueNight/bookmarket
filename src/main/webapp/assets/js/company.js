$(function() {
    $(".main_menu a:nth-child(4)").addClass("active");
    $("#add_category").click(function(){ 
        $(".popup_wrap").addClass("open");
        $("#add_cate").css("display", "inline-block");
        $("#modify_cate").css("display", "none");
        $(".popup .top_area h2").html("출판사 추가")
        $(".popup .top_area p").html("출판사 정보를 입력해주세요")
    })
        $("#add_cate").click(function(){
            if(confirm("출판사를 등록하시겠습니까?") == false) return;
            let ci_name = $("#ci_name").val();
            let ci_status = $("#ci_status option:selected").val();
            let ci_phone = $("#ci_phone").val();
            let ci_email = $("#ci_email").val();
            let ci_address = $("#ci_address").val();

            let data = {
                ci_name:ci_name,
                ci_status:ci_status,
                ci_phone:ci_phone,
                ci_email:ci_email,
                ci_address:ci_address
            }
        $.ajax({
            type:"post",
            url:"/company/add",
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

            $("#ci_name").val("");
            $("#ci_status").val("1").prop("selected", true);
            $("#ci_phone").val("");
            $("#ci_email").val("");
            $("#ci_address").val("");
            $(".popup_wrap").removeClass("open");
        })

        $(".delete_btn").click(function(){
            if(confirm("출판사를 삭제하시겠습니까? \n(이 동작은 되돌릴 수 없습니다)") == false) return;

            let seq = $(this).attr("data-seq");
            $.ajax({
                type:"delete",
                url:"/company/delete?seq="+seq,
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
            $(".popup .top_area h2").html("출판사 정보 수정")
            $(".popup .top_area p").html("수정 정보를 입력해주세요")
            $.ajax({
                type:"get",
                url:"/company/get?seq="+$(this).attr("data-seq"),
                success:function(r) {
                    console.log(r);
                    $("#ci_name").val(r.data.ci_name);
                    $("#ci_status").val(r.data.ci_status).prop("selected", true);
                    $("#ci_phone").val(r.data.ci_phone);
                    $("#ci_email").val(r.data.ci_email);
                    $("#ci_address").val(r.data.ci_address);
                }
            })
        })

        $("#modify_cate").click(function(){
            if(confirm("수정하시겠습니까?") == false) return;
            let ci_name = $("#ci_name").val();
            let ci_status = $("#ci_status option:selected").val();
            let ci_phone= $("#ci_phone").val();
            let ci_email= $("#ci_email").val();
            let ci_address= $("#ci_address").val();

            let data = {
                ci_seq:modify_data_seq,
                ci_name:ci_name,
                ci_status:ci_status,
                ci_phone:ci_phone,
                ci_email:ci_email,
                ci_address:ci_address
            }
            $.ajax({
                type:"patch",
                url:"/company/update",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        })

        $("#search_btn").click(function(){
            location.href="/company?keyword="+$("#keyword").val();
        })
        $("#keyword").keydown(function(e){
            console.log(e.keyCode)
            if(e.keyCode == 13) {
                $("#search_btn").trigger("click");
            }
        })
    })
