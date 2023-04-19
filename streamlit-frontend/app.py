import streamlit as st
import requests
import json

st.set_page_config(layout="centered")
if('cuser' not in st.session_state):
    st.session_state.cuser = False

def postRequest(data, url):
    headers = {"Content-type": "application/json", "Accept": "text/plain"}
    response = requests.post(url=url, data=json.dumps(data), headers=headers)
    return response


def main():
    st.write(
        f"""
        <style>.appview-container .main .block-container{{
        padding-top: {2}rem;}}</style>
    """,
        unsafe_allow_html=True,
    )
    st.title("PES - AirBnB")
    choice = st.sidebar.selectbox("Authentication", ["Login", "Register"])
    headers = {"Content-type": "application/json", "Accept": "text/plain"}
    match choice:
        case "Login":
            st.subheader("Login")
            email = st.text_input("Email")
            password = st.text_input("Password", type="password")
            if st.button("Login"):
                data = {"email": email, "password": password}
                response = requests.get(
                    url="http://localhost:8080/api/login", params=data
                )
                if response.status_code == 200:
                    response = response.json()
                    st.write(response)
                    st.success("Logged in successfully")
                    st.session_state.cuser = True
                    choice = st.sidebar.selectbox("Menu", ["Logout", "Register"])
                else:
                    st.error("Incorrect email or password")

        case Register:
            st.subheader("Register")
            col1, col2, col3 = st.columns([7, 7, 3])
            with col1:
                firstName = st.text_input("First Name")
            with col2:
                lastName = st.text_input("Last Name")
            with col3:
                age = st.number_input("Age", min_value=0, value=0, step=1)
            email = st.text_input("Email")
            col4, col5 = st.columns(2)
            with col4:
                phone = st.text_input("Phone Number")
            with col5:
                password = st.text_input("Password", type="password")
            data = {
                    "email": email,
                    "phone": phone,
                    "password": password,
                    "firstname": firstName,
                    "lastname": lastName,
                    "age": age,
                }
            if st.button("Verify Email"):
                response = requests.post(
                    url="http://localhost:8080/api/verify", 
                    data=json.dumps(data), 
                    headers = headers
                )
                if(response.status_code!=200):
                    st.warning("User already registered, please got to Login page!")
            otp = st.number_input("Enter OTP", value = 0, step = 1)
            data["inputOTP"] = otp
            if st.button("verify OTP"):
                response = requests.post(
                    url="http://localhost:8080/api/register",
                    data=json.dumps(data),
                    headers=headers,
                )
                if response.status_code == 200:
                    st.success("Registered successfully")
                    st.write(response)
                    # if 'cuser' not in st.session_state:
                    #     st.session_state.cuser = response
                else:
                    st.error("Incorrect OTP")
                    

if __name__ == "__main__":
    main()
