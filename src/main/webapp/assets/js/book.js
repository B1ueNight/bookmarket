// teacher.js
$(function() {
    $(".main_nemu a:nth-child(3)").addClass("active");
    $("#search_cate").click(function(){
        $(".category_search").css("display", "block");
    })
    $("cate_search_close").click(function() {
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
                        '<a href="#" data-dep-seq="'+r.list[i].cate_seq+'">'+r.list[i].cate_name+'</a>'+
                    '</li>';
                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function(e){
                    e.preventDefault(); //a태그의 링크 기능 제거
                    let seq = $(this).attr("data-dep-seq");
                    let name = $(this).html();

                    $("#bi_cate_name").attr("data-dep-seq", seq);
                    $("#bi_cate_name").val(name);

                    $(".seacher_result ul").html("");
                    $("#cate_keyword").val("");
                    $(".category_search").css("display", "");
                })
            }
        })
    })

    $("#add_cate").click(function(){

        let book_cate_name = $("#bi_cate_name").attr("data-dep-seq");
        let book_name = $("#bi_name").val();
        let book_sub= $("#bi_sub").val();
        let book_writer = $("#bi_writer").val();
        let book_company = $("#bi_company").val();
        let book_category = $("#bi_cate_name").val();
        let book_status = $("#bi_status option:selected").val();
        let book_stock = $("#bi_stock").val();
        let book_publishing_time = $("#bi_publishing_time").val();
        let book_point = $("#bi_point").val();

        if(book_cate_name == undefined){
            alert("카테고리를 입력해주세요");
            return;
        }
        if(book_name == ''){
            alert("책 제목을 입력해주세요");
            return;
        }

        let data = {
            bi_ci_seq:book_cate_name,
            bi_name:book_name,
            bi_sub:book_sub,
            bi_writer:book_writer,
            bi_cop:book_company,
            bi_cate:book_category,
            bi_status:book_status,
            bi_stock:book_stock,
            bi_pub_dt:book_publishing_time,
            bi_point:book_point
        }

        console.log(data)

        $.ajax({
            type:"post",
            url:"/book/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(e) {
                alert(e.message);
                if(e.status)
                    location.reload();
                }
            })
        })
            
    $("#add_category").click(function(){
        $(".popup_wrap").css("display", "block");
        $("#modify_cate").css("display", "none");
        $("#cancel_cate").css("display", "inline-block");
        $(".popup .top_area h2").html("도서 추가");
        $(".popup .top_area p").html("도서 정보를 입력하세요");
    })
    $("#cancel_cate").click(function(){
        if(confirm("취소하시겠습니까?\n(입력한 내용은 저장되지 않습니다.)") == false) return;

        $("book_cate_name").attr("data-dep-seq", "");
        $("#bi_name").val("");
        $("#bi_sub").val("");
        $("#bi_writer").val("");
        $("#bi_company").val("");
        $("#bi_status").val("");
        $("#bi_stock").val("");
        $("#bi_pub_dt").val("");
        $("#bi_point").val("");
        
        $(".popup_wrap").css("display", "")
    })


    $("#search_btn").click(function(){
        let type = $("#search_type option:selected").val();
        let keyword = $("#keyword").val();

        location.href = "/book?type="+type+"&keyword="+keyword;
    })
})