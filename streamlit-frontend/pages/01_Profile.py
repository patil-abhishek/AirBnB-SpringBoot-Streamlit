import streamlit as st
import requests
import json 

def main():
    if(st.session_state.cuser==False):
        st.warning("You haven't logged in!")
    else:
        response = requests.get(url="http://localhost:8080/api/user/profile").json()
        st.write(response)
        st.markdown("""---""")
        st.subheader("Update Profile")
        col1, col2, col3 = st.columns([4,4,2])
        with col1:
            firstname = st.text_input("First Name", value = response['firstname'])
        with col2:
            lastname = st.text_input("Last Name", value = response['lastname'])
        with col3:
            age = st.number_input("Age", min_value=0, value = response['age'], step=1)
        col4, col5 = st.columns(2)
        with col4:
            phone = st.text_input("Phone Number", value = response['phone'])
        with col5:
            password = st.text_input("Password", type="password")
        response['firstname'] = firstname
        response['lastname'] = lastname
        response['age'] = age
        response['phone'] = phone
        response['password'] = password
        if st.button("Update"):
            response = requests.post(
                url="http://localhost:8080/api/user/updateProfile",
                data=json.dumps(response),
                headers = {"Content-type": "application/json", "Accept": "text/plain"}
            )
            if response.status_code == 200:
                st.success("Profile updated successfully!")
            else:
                st.error("Profile update failed!")
        

if __name__ == '__main__':
    main()
