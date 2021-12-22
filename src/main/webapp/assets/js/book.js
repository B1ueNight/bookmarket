$(function() {
    $(".main_menu a:nth-child(3)").addClass("active");
    $("#search_cate").click(function(){
        $(".category_search").css("display", "block");
    })
    $("#cate_search_close").click(function() {
        $(".category_search").css("display", "");
    });
    $("#cate_keyword").keyup(function(e){
        if(e.keyCode == 13) $("#cate_search_btn").trigger("click");
    })

    $("#cate_search_btn").click(function() {
        $.ajax({
            url:"/category/keyword?keyword="+$("#cate_keyword").val(),
            type:"get",
            success:function(r) {
                console.log(r);
                $(".search_result ul").html("");
                for(let i=0; i<r.list.length; i++){
                    let tag=
                    '<li>'+
                        '<a href="#" data-cate-seq="'+r.list[i].cate_seq+'">'+r.list[i].cate_name+'</a>'
                    '</li>';
                    $(".search_result ul").append(tag);
                    }
                    $(".search_result ul a").click(function(e){
                        e.preventDefault();
                        let seq = $(this).attr("data-cate-seq");
                        let name = $(this).html();
    
                        $("#bi_cate_name").attr("data-cate-seq", seq);
                        $("#bi_cate_name").val(name);
    
                        $(".seacher_result ul").html("");
                        $("#cate_keyword").val("");
                        $(".category_search").css("display", "");
                    })
                }
            })
        })

    $("#add_category").click(function(){ 
        $(".popup_wrap").addClass("open");
        $("#add_cate").css("display", "inline-block");
        $("#modify_cate").css("display", "none");
        $(".popup .top_area h2").html("도서 추가")
        $(".popup .top_area p").html("도서 정보를 입력해주세요")
    })
        $("#add_cate").click(function(){
            if(confirm("도서를 등록하시겠습니까?") == false) return;
            let bi_cate_name = $("#bi_cate_name").attr("data-cate-seq");
            let bi_name = $("#bi_name").val();
            let bi_code= $("#bi_code").val();
            let bi_writer = $("#bi_writer").val();
            let bi_company = $("#bi_company").val();
            let bi_status = $("#bi_status option:selected").val();
            let bi_stock = $("#bi_stock").val();
            let bi_pub_dt = $("#bi_pub_dt").val();
            let bi_point = $("#bi_point").val();

            if(bi_cate_name == undefined){
                alert("카테고리를 입력해주세요");
                return;
            }
            let data = {
                bi_ci_seq:bi_cate_name,
                bi_name:bi_name,
                bi_code:bi_code,
                bi_writer:bi_writer,
                bi_company:bi_company,
                bi_status:bi_status,
                bi_stock:bi_stock,
                bi_pub_dt:bi_pub_dt,
                bi_point:bi_point
            }
            console.log(data);
        $.ajax({
            type:"post",
            url:"/book/add",
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

            $("#book_cate_name").val("data-cate-seq", "");
            $("#bi_name").val("");
            $("#bi_code").val("");
            $("#bi_writer").val("");
            $("#bi_company").val("");
            $("#bi_status").val("").val("1").prop("selected", true);
            $("#bi_stock").val("");
            $("#bi_pub_dt").val("");
            $("#bi_point").val("");
            
            $(".popup_wrap").removeClass("open");
        })

        $(".delete_btn").click(function(){
            if(confirm("도서를 삭제하시겠습니까? \n(이 동작은 되돌릴 수 없습니다)") == false) return;

            let seq = $(this).attr("data-seq");
            $.ajax({
                type:"delete",
                url:"/book/delete?seq="+seq,
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
            $(".popup .top_area h2").html("도서 정보 수정")
            $(".popup .top_area p").html("수정 정보를 입력해주세요")
            $.ajax({
                type:"get",
                url:"/book/get?seq="+$(this).attr("data-seq"),
                success:function(r) {
                    console.log(r);
                    $("#bi_cate_name").attr("data-cate-seq", r.bi_ci_seq);
                    $("#bi_cate_name").val(r.data.bi_cate_name);
                    $("#bi_name").val(r.data.bi_name);
                    $("#bi_code").val("******").prop("disabled", true);
                    $("#bi_writer").val(r.data.bi_writer);
                    $("#bi_company").val(r.data.bi_company);
                    $("#bi_status").val(r.data.bi_status).prop("selected", true);
                    $("#bi_stock").val(r.data.bi_stock);
                    $("#bi_pub_dt").val(r.data.bi_pub_dt);
                    $("#bi_point").val(r.data.bi_point);
                }
            })
        })

        $("#modify_cate").click(function(){
            if(confirm("수정하시겠습니까?") == false) return;
            let bi_cate_name = $("#bi_cate_name").attr("data-cate-seq");
            let bi_name = $("#bi_name").val();
            let bi_code= $("#bi_code").val();
            let bi_writer = $("#bi_writer").val();
            let bi_company = $("#bi_company").val();
            let bi_status = $("#bi_status option:selected").val();
            let bi_stock = $("#bi_stock").val();
            let bi_pub_dt = $("#bi_pub_dt").val();
            let bi_point = $("#bi_point").val();

            let data = {
                bi_seq:modify_data_seq,
                bi_ci_seq:bi_cate_name,
                bi_name:bi_name,
                bi_code:bi_code,
                bi_writer:bi_writer,
                bi_company:bi_company,
                bi_status:bi_status,
                bi_stock:bi_stock,
                bi_pub_dt:bi_pub_dt,
                bi_point:bi_point
            }
            $.ajax({
                type:"patch",
                url:"/book/modify",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        })

        $("#search_btn").click(function(){
            location.href="/book?keyword="+$("#keyword").val();
        })
        $("#keyword").keydown(function(e){
            console.log(e.keyCode)
            if(e.keyCode == 13) {
                $("#search_btn").trigger("click");
            }
        })
    })
