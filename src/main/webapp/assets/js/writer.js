$(function() {
    $(".main_menu a:nth-child(6)").addClass("active");
    $("#search_company").click(function(){
        $(".company_search").css("display", "block");
    })
    $("#company_search_close").click(function() {
        $(".company_search").css("display", "");
    });
    $("#company_keyword").keyup(function(e){
        if(e.keyCode == 13) $("#company_search_btn").trigger("click");
    })
    $("#company_search_btn").click(function() {
        $.ajax({
            url:"/company/keyword?keyword="+$("#company_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.list.length; i++){
                    let str_status = "";
                    if(r.list[i].ci_status == 1) str_status = "대형 출판사"
                    if(r.list[i].ci_status == 2) str_status = "소형 출판사"
                    if(r.list[i].ci_status == 3) str_status = "독립 출판사"
                    if(r.list[i].ci_status == 4) str_status = "1인 출판사"

                    let tag=
                    '<li>'+
                        '<a href="#" data-co-seq="'+r.list[i].ci_seq+'">'+r.list[i].ci_name+'</a>'+
                        '<span class="status'+r.list[i].ci_status+'">'+str_status+'</span>'+
                    '</li>';
                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault();
                    let seq = $(this).attr("data-co-seq");
                    let name = $(this).html();

                    $("#wi_company").attr("data-co-seq", seq);
                    $("#wi_company").val(name);

                    $(".seacher_result ul").html("");
                    $("#company_keyword").val("");
                    $(".company_search").css("display", "");
                })
            }
        })
    })
    $("#add_category").click(function(){ 
        $(".popup_wrap").addClass("open");
        $("#add_cate").css("display", "inline-block");
        $("#modify_cate").css("display", "none");
        $(".popup .top_area h2").html("작가 추가")
        $(".popup .top_area p").html("작가 정보를 입력해주세요")
    })
        $("#add_cate").click(function(){
            if(confirm("작가를 등록하시겠습니까?") == false) return;
            let wi_name = $("#wi_name").val();
            let wi_birth = $("#wi_birth").val();
            let wi_email = $("#wi_email").val();
            let wi_company = $("#wi_company").attr("data-co-seq");

            let data = {
                wi_name:wi_name,
                wi_birth:wi_birth,
                wi_email:wi_email,
                wi_ci_seq:wi_company
            }
        $.ajax({
            type:"post",
            url:"/writer/add",
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

            $("#wi_name").val("");
            $("#wi_birth").val("");
            $("#wi_email").val("");
            $("#wi_company").val("data-co-seq", "");

            $(".popup_wrap").removeClass("open");
        })

        $(".delete_btn").click(function(){
            if(confirm("작가 정보를 삭제하시겠습니까? \n(이 동작은 되돌릴 수 없습니다)") == false) return;

            let seq = $(this).attr("data-seq");
            $.ajax({
                type:"delete",
                url:"/writer/delete?seq="+seq,
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
            $(".popup .top_area h2").html("작가 정보 수정")
            $(".popup .top_area p").html("수정 정보를 입력해주세요")
            $.ajax({
                type:"get",
                url:"/writer/get?seq="+$(this).attr("data-seq"),
                success:function(r) {
                    console.log(r);
                    $("#wi_name").val(r.data.wi_name);
                    $("#wi_birth").val(r.data.wi_birth);
                    $("#wi_email").val(r.data.wi_email);
                    $("#wi_company").attr("data-co-seq", r.wi_ci_seq);
                    $("#wi_company").val(r.data.wi_company);
                }
            })
        })

        $("#modify_cate").click(function(){
            if(confirm("수정하시겠습니까?") == false) return;
            let wi_name = $("#wi_name").val();
            let wi_birth= $("#wi_birth").val();
            let wi_email= $("#wi_email").val();
            let wi_company= $("#wi_company").attr("data-co-seq");

            let data = {
                wi_seq:modify_data_seq,
                wi_name:wi_name,
                wi_birth:wi_birth,
                wi_email:wi_email,
                wi_ci_seq:wi_company
                }
                
            $.ajax({
                type:"patch",
                url:"/writer/update",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        })

    $("#search_btn").click(function(){
        let type = $("#search_type option:selected").val();
        let keyword = $("#keyword").val();

        location.href = "/writer?type="+type+"&keyword="+keyword;
    })
        $("#keyword").keydown(function(e){
            console.log(e.keyCode)
            if(e.keyCode == 13) {
                $("#search_btn").trigger("click");
            }
        })
    })
