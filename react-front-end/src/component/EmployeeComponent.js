import React, { Component } from 'react';
import EmployeeService from '../service/EmployeeService';

class EmployeeComponent extends Component {
    constructor(props) {
        super(props);

        this.state = {
            employee  : {},
            department : {},
            organization : {}
        }
    }
    
componentDidMount(){
    EmployeeService.getEmployee().then((response) =>{  
        this.setState({department : response.data.departmentDto})
        this.setState({employee : response.data.employeeDto})
        this.setState({organization : response.data.organizationDto})

        console.log(this.state.department)
        console.log(this.state.employee)
        console.log(this.state.organization)
    })
}

    render() {
        return (
            <div><br></br>
                <div  className='card col-md-6 offset-md-3'>
                    <h3 className='text-center card-header'> View Employee Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                            <p><strong>Employee First Name: </strong> {this.state.employee.firstName}</p>
                            <p><strong>Employee Last Name: </strong> {this.state.employee.lastName}</p>
                            <p><strong>Employee Email: </strong> {this.state.employee.email}</p>
                        </div>
                    </div><br></br>
                    <h3 className='text-center card-header'> View Department Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                                <p><strong>Department Name: </strong> {this.state.department.departmentName}</p>
                                <p><strong>Department Code: </strong> {this.state.department.departmentCode}</p>
                        </div>
                    </div><br></br>
                    <h3 className='text-center card-header'> View Organization Details</h3>
                    <div className='card-body'>
                        <div className='row'>
                                <p><strong>Organization Name: </strong> {this.state.organization.organizationName}</p>
                                <p><strong>Organization Code: </strong> {this.state.organization.organizationCode}</p>
                                <p><strong>Organization Creation Date: </strong> {this.state.organization.organizationCreationDate}</p>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default EmployeeComponent;