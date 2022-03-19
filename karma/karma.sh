#!/bin/bash

if [ -d "/home/coder/project/workspace/angularapp" ]
then
    echo "project folder present"
    cp /home/coder/project/workspace/karma/karma.conf.js /home/coder/project/workspace/angularapp/karma.conf.js;
    
    # checking for app component
    if [ -d "/home/coder/project/workspace/angularapp/src/app" ]
    then
        cp /home/coder/project/workspace/karma/app.component.spec.ts /home/coder/project/workspace/angularapp/src/app/app.component.spec.ts;
    else
        echo "FE_AppTest FAILED";
    fi

    # checking for auth component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/auth" ]
    then
        cp /home/coder/project/workspace/karma/app.component.spec.ts /home/coder/project/workspace/angularapp/src/app/auth/auth.component.spec.ts;
    else
        echo "FE_AuthTest FAILED";
    fi

    # checking for login component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/auth/login" ]
    then
        cp /home/coder/project/workspace/karma/login.component.spec.ts /home/coder/project/workspace/angularapp/src/app/auth/login/login.component.spec.ts;
    else
        echo "FE_LoginTest FAILED";
    fi 

    #checking for signup component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/auth/signup" ]
    then
        cp /home/coder/project/workspace/karma/signup.component.spec.ts /home/coder/project/workspace/angularapp/src/app/auth/signup/signup.component.spec.ts;
    else
        echo "FE_SignupTest FAILED";
    fi 
    
    # checking for cart component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/cart" ]
    then
        cp /home/coder/project/workspace/karma/cart.component.spec.ts /home/coder/project/workspace/angularapp/src/app/cart/cart.component.spec.ts;
    else
        echo "FE_CartTest FAILED";
    fi 

    #checking for homepage component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/homepage" ]
    then
        cp /home/coder/project/workspace/karma/homepage.component.spec.ts /home/coder/project/workspace/angularapp/src/app/homepage/homepage.component.spec.ts;
    else
        echo "FE_HomepageTest FAILED";
    fi 

    # checking for order component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/order" ]
    then
        cp /home/coder/project/workspace/karma/order.component.spec.ts /home/coder/project/workspace/angularapp/src/app/order/order.component.spec.ts;
    else
        echo "FE_OrderTest FAILED";
    fi 

    # checking for navigation component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/adminhomepage" ]
    then
        cp /home/coder/project/workspace/karma/adminhomepage.component.spec.ts /home/coder/project/workspace/angularapp/src/app/adminhomepage/adminhomepage.component.spec.ts;
    else
        echo "FE_AdminHomePageTest FAILED";
    fi 

    # checking for add product component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/addproduct" ]
    then
        cp /home/coder/project/workspace/karma/addproduct.component.spec.ts /home/coder/project/workspace/angularapp/src/app/addproduct/addproduct.component.spec.ts;
    else
        echo "FE_AddproductTest FAILED";
    fi 

    # checking for view component
    if [ -d "/home/coder/project/workspace/angularapp/src/app/view" ]
    then
        cp /home/coder/project/workspace/karma/view.component.spec.ts /home/coder/project/workspace/angularapp/src/app/view/view.component.spec.ts;
    else
        echo "FE_ViewTest FAILED";
    fi 

    cd /home/coder/project/workspace/angularapp/;
    npm test;
else   
    echo "FE_AppTest FAILED";
    echo "FE_AuthTest FAILED";
    echo "FE_LoginTest FAILED";
    echo "FE_SignupTest FAILED";
    echo "FE_CartTest FAILED";
    echo "FE_HomepageTest FAILED";
    echo "FE_OrderTest FAILED";
    echo "FE_AdminHomePageTest FAILED";
    echo "FE_AddproductTest FAILED";
    echo "FE_ViewTest FAILED";
fi
