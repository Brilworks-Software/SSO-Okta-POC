import React, { useEffect, useState } from 'react';
import { useOktaAuth } from '@okta/okta-react';
import { toRelativeUrl } from '@okta/okta-auth-js';
import { Outlet } from 'react-router-dom';
import { Spin } from 'antd';
import { Layout } from 'antd';
import SideMenu from './SideMenu';

const { Header, Footer, Sider, Content } = Layout;

function RequiredAuth() {
  const { oktaAuth, authState } = useOktaAuth();
  const [login, setLogin] = useState(false)
  useEffect(() => {
    if (!authState) {
      return;
    }

    if (!authState.isAuthenticated) {
      const originalUri = toRelativeUrl(window.location.href, window.location.origin);
      oktaAuth.setOriginalUri(originalUri);
      oktaAuth.signInWithRedirect();
    }
  }, [oktaAuth, authState]);

  useEffect(()=>{
if(authState &&  authState.isAuthenticated){
  oktaAuth.getUser().then((info) => {
   console.log(authState)
    const data = {
      ACCESS_TOKEN:authState.accessToken.accessToken,
      EMAIL:info.email
    }
    fetch('http://localhost:8080/api/auth/login', {
      method: 'POST', // or 'PUT', 'PATCH', 'DELETE', etc. depending on the HTTP method you need
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data), // Convert the data to JSON format
    })
    .then(response =>response.status === 200 && setLogin(true))
    .catch(error =>setLogin(false));
  });


}else{
  setLogin(false)
}
  },[authState ])



  if (!login) {


    return (
      <div style={{
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        height: '100vh',
      }} >
        <Spin size="large" />
      </div>
    );
  }

  return (
    login &&
    <Layout style={{ height: "100vh" }} >
      <Header style={{ color: "white" }}>Header</Header>
      <Layout>
        <Sider><SideMenu /></Sider>
        <Content style={{ margin: '24px' }}>
          <Outlet />
        </Content>
      </Layout>
      <Footer>Footer</Footer>
    </Layout>
  );

}

export default RequiredAuth;