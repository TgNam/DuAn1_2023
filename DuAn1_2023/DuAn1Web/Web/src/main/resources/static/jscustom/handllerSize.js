function getSize(){
    let wapperSize = document.querySelector(".product__details__option__size");
    let id =document.querySelector(".product__details__option").getAttribute("data-id");
    fetch(`/admin/product/product-details/get-size-by-productId?id=${id}`)
        .then(response => response.json())
        .then(data => {
            let html="";
            data.forEach(item=>{
               html +=` <label  class="sizeLabel" for="${item.nameSize}">
                            ${item.nameSize}
                            <input name="size" oninput="getColor(this)"  type="radio" id="${item.nameSize}">
                        </label>`
            })
            wapperSize.innerHTML = html;
        })
        .catch(error => {
            // Xử lý lỗi
            console.error(error);
        });
}
getSize();

function getColor(e){
    let sizeName = e.getAttribute("id");
    let id =document.querySelector(".product__details__option").getAttribute("data-id");
    let wapperSize = document.querySelector(".product__details__option__size");
    let wapperColor = document.querySelector(".product__details__option__color");
    if(wapperSize.querySelector(".active")){
        wapperSize.querySelector(".active").classList.remove("active");
        console.log(true)
    }

    e.parentElement.classList.add("active")
    fetch(`/admin/product/product-details/get-by-size?sizeName=${sizeName}&id=${id}`)
        .then(response => response.json())
        .then(data => {
            let html="";
            data.forEach(item=>{
                html +=`  <label class="colorLable p-3" for="${item.color.colorName}" >
                              <input name="color" type="radio" id="${item.color.colorName}">
                          </label> <hr>
                          <span>${item.color.colorName}</span>
                        `
            })
            wapperColor.innerHTML = html;
        })
        .catch(error => {
            // Xử lý lỗi
            console.error(error);
        });
}