let isBurgerOpen = true;
makePromptWindow(()=>setUser());

window.addEventListener('load', removeTable);

console.log(isAdmin);

function removeTable(){
    let hero = document.querySelector('.hero');
    let table = hero.querySelector('table');
    let form = hero.querySelector('form');
    if(table){
        console.log('removed')
        table.remove();
    }
    if(form){
        console.log('form')
        form.remove();
    }
}
function makeNav(element, list){

    let leftNav = document.createElement('div');
    let centerNav = document.createElement('div');
    let rightNav = document.createElement('div');

    leftNav.className = 'left-nav';
    centerNav.className = 'center-nav';
    rightNav.className = 'right-nav';

    let navLogo = document.createElement('div');
    let navUL = document.createElement('ul');

    leftNav.appendChild(navLogo);
    centerNav.appendChild(navUL);

    element.appendChild(leftNav);
    element.appendChild(centerNav);
    element.appendChild(rightNav);

    makeAchorList(document.querySelector('.nav-bar .center-nav ul'), list);
}
function makeBurger(element, openFunction, closeFunction, name){

    let burgerBtn = document.createElement('button');
    let burgerDiv = document.createElement('div');
    let topLine = document.createElement('span');
    let centerLine = document.createElement('span');
    let bottomLine = document.createElement('span');

    topLine.id = 'topLine';
    centerLine.id = 'centerLine';
    bottomLine.id = 'bottomLine';

    burgerDiv.appendChild(topLine);
    burgerDiv.appendChild(centerLine);
    burgerDiv.appendChild(bottomLine);
    burgerBtn.appendChild(burgerDiv);

    element.appendChild(burgerBtn);

    function makeBurgerClose(element){
        document.querySelector('.'+element.className+' button div #topLine').style.cssText = 'width:50%; transition: width 1s ease;';
        document.querySelector('.'+element.className+' button div #bottomLine').style.cssText = "align-self: flex-end; width: 50%; transition: width 1s ease;";

        setTimeout(()=>{

            document.querySelector('.'+element.className+' button div #topLine').style.cssText = "width:50%;transform-origin:left; margin-left: 7px; transition: transform 1s ease; transform: rotate(45deg)"
            document.querySelector('.'+element.className+' button div #centerLine').style.cssText = "transition: transform 1s ease; transform: rotate(-45deg)"
            document.querySelector('.'+element.className+' button div #bottomLine').style.cssText = "align-self: flex-end; width: 50%; transform-origin:right; margin-right: 7px; transition: transform 1s ease; transform: rotate(45deg"
        },1000)

        setTimeout(()=>{
            document.querySelector('.'+element.className+' button').disabled = false;
        },2000);

        setTimeout(()=>{
            openFunction();
        },2000);

    }
    function makeBurgerLines(){
        document.querySelector('.'+element.className+' button div #topLine').style.cssText = "width:50%;transform-origin:left; margin-left: 7px; transition: transform 1s ease; transform: rotate(0deg)"
        document.querySelector('.'+element.className+' button div #centerLine').style.cssText = "transition: transform 1s ease; transform: rotate(0deg)"
        document.querySelector('.'+element.className+' button div #bottomLine').style.cssText = "align-self: flex-end; width: 50%; transform-origin:right; margin-right: 7px; transition: transform 1s ease; transform: rotate(0deg"

        setTimeout(()=>{
            document.querySelector('.'+element.className+' button div #topLine').style.cssText = 'width:100%; transition: width 1s ease;';
            document.querySelector('.'+element.className+' button div #bottomLine').style.cssText = "align-self: flex-end; width: 100%; transition: width 1s ease;";
        },1000);

        setTimeout(()=>{
            document.querySelector('.'+element.className+' button').disabled = false;
        },2000);

        setTimeout(()=>{
            closeFunction();
        },2000);
    }

    document.querySelector('.'+element.className +' button').addEventListener('click', ()=>{
        if(isBurgerOpen){
            document.querySelector('.'+element.className +' button').disabled = true;
            makeBurgerClose(element);
            isBurgerOpen = false
        }
        else if (!isBurgerOpen){
            document.querySelector('.'+element.className +' button').disabled = true;
            makeBurgerLines(element);
            isBurgerOpen=true;
        }
    });
    burgerBtn.className = name;
}
function openMenu(){
    console.log('oppen');
}
function closeMenu(){
    console.log('close')
}
function makeLogo(element, img){

}
function makeList(element, list){
    list.forEach(e => {
        let liElement = document.createElement('li');
        liElement.textContent = e;
        liElement.className = e;
        element.appendChild(liElement);
    });
    element.style.cssText = ''
}
async function makeTableFromJson(tableParent,endpoint){
    removeTable();
    console.log(endpoint)
    let data = await fetchJson(endpoint);

    let table = document.createElement('table');
    let tHead = document.createElement('thead');
    let tr = document.createElement('tr');
    let tBody = document.createElement('tbody');

    const list = [];

    Object.keys(data[0]).forEach(column=>{
        let th = document.createElement('th');
        th.textContent = column;
        tr.appendChild(th);
    });

    data.forEach(element =>{
        let tBodyRow = document.createElement('tr');

        Object.values(element).forEach(column =>{
            let td = document.createElement('td');
            td.textContent = column;
            tBodyRow.appendChild(td);
        });

        if(isAdmin) {
            let button1 = document.createElement('button');
            button1.textContent = 'UPDATE';
            button1.addEventListener('click', () => {
                console.log('UPDATE USER ' + tBodyRow.cells[0].textContent)
                makePromptWindow(() => manipulateElement(id, endpoint, () => updateElement(id, endpoint)));
            });

            let id = tBodyRow.cells[0].textContent;
            let button2 = document.createElement('button');
            button2.textContent = 'REMOVE';
            button2.addEventListener('click', () => {
                console.log('REMOVE USER ' + id)
                makePromptWindow(() => manipulateElement(id, endpoint, () => removeElement(id, endpoint)));
            });
            tBodyRow.appendChild(button1);
            tBodyRow.appendChild(button2);
        }
        else {
            if (endpoint.toUpperCase().includes('ITEMS')) {
                let id = tBodyRow.cells[0].textContent;
                let button1 = document.createElement('button');
                button1.textContent = 'ADD2CART';
                button1.addEventListener('click', () => {
                    console.log('UPDATE USER ' + tBodyRow.cells[0].textContent)
                    makePromptWindow(() => manipulateElement(id, endpoint, () => add2Cart()));
                });
                tBodyRow.appendChild(button1);


                let button2 = document.createElement('button');
                button2.textContent = 'CHECKOUT';
                button2.addEventListener('click', () => {
                    console.log('UPDATE USER ' + tBodyRow.cells[0].textContent)
                    makePromptWindow(() => manipulateElement(id, endpoint, () => buyItem()));
                });
                tBodyRow.appendChild(button2);
            }
        }

        tBody.appendChild(tBodyRow);
    });

    tHead.appendChild(tr);
    table.appendChild(tHead);
    table.appendChild(tBody);
    document.querySelector('.hero').appendChild(table);
    // table.appendChild(tHead);
    //tableParent.appendChild(table);
}
async function fetchJson(endpoint){
    const token = sessionStorage.getItem('token');
    const response = await fetch('http://localhost:9090' + endpoint, {
        headers: {
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "http://localhost:9090"+endpoint,
            "Access-Control-Allow-Methods": "OPTIONS, POST, GET, PUT,DELETE",
            "Access-Control-Allow-Headers": "Content-Type",
            "Authorization": `Bearer ${token}`
        }
    });
    console.log(response)
    const data = await response.json();
    return data;
}
async function deleteItemFromDb(endpoint, itemId) {
    const response = await fetch(`${endpoint}/${itemId}/delete`, {
        method: 'DELETE'
    });

    if (response.ok) {
        console.log('deleted')
        return true;
    } else {
        // Error occurred while deleting item
        const errorData = await response.json();
        throw new Error(errorData.message);
    }
}
async function makeListFromJson(ul,endpoint){
    let data = await fetchJson(endpoint);
    const list = [];
    data.forEach(element =>{
        list.push(Object.values(element));
        console.log(list)
    });

    makeList(document.querySelector(ul), list)
}
function menuPopOutL2R(element){
    let menu = document.querySelector('.hero .menu' );
    transitionWidth(menu, '100vw');
}
function menuPopInL2R(element){
    let menu = document.querySelector('.hero .menu' );
    transitionWidth(menu, '0vw');
}
function transitionWidth(element, newWidth){
    element.style.width = newWidth;
}
function makeAchorList(element, list){
    list.forEach(e => {
        let liElement = document.createElement('li');
        let a = document.createElement('a');
        a.className = e + '-aTag';
        a.setAttribute('href', '#');
        a.textContent = e;
        liElement.appendChild(a);
        liElement.className = e + '-li';
        element.appendChild(liElement);
    });
    element.style.cssText = ''
}
function setHref(aTag, endpoint) {
    if(endpoint == '/index'){
        aTag.setAttribute('href', '/index');
    }
    else {
        aTag.setAttribute('href', '#');
        aTag.setAttribute('onclick', 'event.preventDefault(); makeTableFromJson(document.querySelector(".hero"), "' + endpoint + '")');
    }
}
function makePromptWindow(event){
    let overlay = document.createElement('div');
    let promptMessage = document.createElement('div');
    let topDiv = document.createElement('div');
    let bottomDiv = document.createElement('div');

    overlay.className = 'overlay';
    promptMessage.className = 'prompt-message';
    topDiv.className = 'top-div';
    bottomDiv.className = 'bottom-div';
    promptMessage.appendChild(topDiv);
    promptMessage.appendChild(bottomDiv);

    overlay.appendChild(promptMessage);
    document.querySelector('.hero').appendChild(overlay);

    event();
}
function manipulateElement(id, endpoint, event){

    let promptMessage = document.querySelector('.prompt-message .bottom-div');

    let yesBtn = document.createElement('button');
    let noBtn = document.createElement('button');

    yesBtn.textContent = 'Yes';
    noBtn.textContent = 'No'

    noBtn.addEventListener('click', ()=>{
        document.querySelector('.overlay').remove();
    });

    yesBtn.addEventListener('click', () => {
        document.querySelector('.overlay').remove();
        event(id,endpoint);
    });

    promptMessage.appendChild(noBtn);
    promptMessage.appendChild(yesBtn);

}
function updateElement(id, endpoint){
    removeTable();
    console.log(endpoint + "/" + id)
    fetchJson(endpoint + "/" + id ).then((data)=> {
        console.log(data)
        makeForm(data, id, endpoint)
    });

}
function removeElement(id, endpoint){
    deleteItemFromDb(endpoint, id)
        .then(() => {
            makeTableFromJson(document.querySelector('.hero'), endpoint);
            //document.querySelector('.overlay').remove();
        })
        .catch(error => {
            console.error('Error deleting item:', error);
        });
};
function add2Cart(){
    console.log('add 2 cart')
}
function buyItem(){

    console.log('element bought');
}
function makeForm(list, id, endpoint){


    const form = document.createElement('form');
    form.setAttribute('id', 'myForm');
    form.setAttribute('method', 'PUT');
    form.setAttribute('action', endpoint+'/'+id+'/update');

    for (let key in list) {
        if (list.hasOwnProperty(key)) {
            console.log(key, list[key]);
            if (key != 'id') {
                const label = document.createElement('label');
                label.textContent = key.toUpperCase();
                const input = document.createElement('input');
                input.setAttribute('type', 'text');
                input.setAttribute('name', key);
                form.appendChild(label);
                form.appendChild(input);
                form.appendChild(document.createElement('br'));
            }
        }
    }


    const submitBtn = document.createElement('button');
    submitBtn.setAttribute('type', 'submit');
    submitBtn.textContent = 'Submit';

    submitBtn.addEventListener('click', (event)=>{
        event.preventDefault();
        const formData = new FormData(form);
        const formDataObject = Object.fromEntries(formData.entries());
        console.log(formDataObject);
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(Object.fromEntries(formData.entries()))
        };

        fetch(endpoint + '/' + id + '/update', requestOptions)
            .then(response => {
                if (response.ok) {
                    makeTableFromJson(document.querySelector('.hero'), endpoint);
                } else {
                    throw new Error('Request failed');
                }
            })
            .catch(error => {
                // Handle error
            });

    });

// Add the submit button to the form
    form.appendChild(submitBtn);

// Append the form to the document body
    document.querySelector('.hero').appendChild(form);

}
function setUser(){
    let promptMessage = document.querySelector('.prompt-message .bottom-div');

    let yesBtn = document.createElement('button');
    let noBtn = document.createElement('button');

    yesBtn.textContent = 'User';
    noBtn.textContent = 'Admin'

    noBtn.addEventListener('click', ()=>{
        isAdmin = true;
        var myList = [ "HOME","CUSTOMERS", "ITEMS", "ORDERS" ];
        makeSite(myList);
        getToken();
        document.querySelector('.overlay').remove();
    });

    yesBtn.addEventListener('click', () => {
        isAdmin=false;
        var myList = [ "HOME", "ITEMS"];
        makeSite(myList)
        document.querySelector('.overlay').remove();
    });

    promptMessage.appendChild(noBtn);
    promptMessage.appendChild(yesBtn);
}
function makeSite(list){
    makeNav(document.querySelector('.nav-bar'), list);

    makeBurger(document.querySelector('.left-nav'), ()=> menuPopOutL2R(document.querySelector('.hero')), ()=>menuPopInL2R(document.querySelector('.hero div')), 'leftMenu');

    if(isAdmin){
        setHref(document.querySelector('.HOME-aTag'), '/index');
        setHref(document.querySelector('.CUSTOMERS-aTag'), '/customers');
        setHref(document.querySelector('.ITEMS-aTag'), '/items');
        setHref(document.querySelector('.ORDERS-aTag'), '/orders')
    }
    else{
        setHref(document.querySelector('.HOME-aTag'), '/index');
        setHref(document.querySelector('.ITEMS-aTag'), '/items');
    }
}
async function getToken() {
    const response = await fetch('http://localhost:9090/authenticate', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin': 'http://localhost:9090',
            'Access-Control-Allow-Methods': 'OPTIONS, POST, GET, PUT, DELETE',
            'Access-Control-Allow-Headers': 'Content-Type, Authorization'
        },
        body: JSON.stringify({
            username: 'Admin',
            password: 'admin'
        })
    });

    if (response.ok) {
        const data = await response.json();
        const token = data.token;
        // Store the token in sessionStorage
        sessionStorage.setItem('token', token);
    } else {
        throw new Error('Failed to get token');
    }
}
function validateUser(){

}

//makeTableFromJson(document.querySelector('.hero'), '/customers')
//makeLogo(document.querySelector('.nav-bar .left-nav'))
