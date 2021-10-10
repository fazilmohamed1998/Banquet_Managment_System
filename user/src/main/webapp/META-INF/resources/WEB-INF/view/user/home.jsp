<%@ taglib uri="http://htmlcompressor.googlecode.com/taglib/compressor" prefix="compress" %>
<compress:html enabled="${isHtmlCompressionEnabled}" compressJavaScript="true"
               compressCss="true">
  <%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
  <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
  <html>
  <head>
    <jsp:include page="../app/resource.jsp"/>
    <!-- Module CSS -->
    <link href="/static/user/css/user.css" rel="stylesheet"/>
  </head>
  <body>
  <div class="content">
    <%--<button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
            data-target="#searchModel" data-backdrop="static">
      <i class="far fa-search"></i>
      <spring:message code="lbl.common.search"/>
    </button>--%>
    <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
            data-target="#reportModal" data-backdrop="static">
      <i class="far fa-analytics"></i>
      <spring:message code="lbl.common.reports"/>
    </button>
      <%--<h1>Hashan</h1>--%>


    <!--Add Users-->

    <div id="userl-container" class="row">
      <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
        <div class="card hover-shadow">
          <div class="add-card card-body d-flex justify-content-between" data-toggle="modal"
               data-target="#editUserModal" data-backdrop="static">
            <i class="far fa-plus hover-translate-y-n3"></i>
          </div>
        </div>
      </div>

      <c:forEach items="${users}" var="user">
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
          <div class="card hover-shadow">
            <div class="card-body d-flex justify-content-between">
              <a href="#"
                 class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                 data-bw="3px">
                <img alt="Image placeholder"
                     src="/static/user/img/user ${user.id}.jpeg">
              </a>
              <div class="details vertical-middle">
                <span class="type"><b>${user.userRole.name}</b></span><br>
                <span class="customer-name">${user.name}</span><br>
                <span class="user-contact">${user.contactNo}</span><br>
                <span class="badge badge-success mt-3">${user.status.name}</span>
              </div>
            </div>
            <div class="card-footer">
              <div class="actions d-flex justify-content-between px-4">
                <a class="action-item hover-translate-y-n3" data-id="${user.id}"
                   data-toggle="modal" data-target="#editUserModal" data-backdrop="static">
                  <i class="far fa-edit"></i>
                </a>
                <a class="action-item text-danger hover-translate-y-n3" data-id="${user.id}"
                   data-toggle="modal" data-target="#deleteUser" data-backdrop="static">
                  <i class="far fa-trash-alt"></i>
                </a>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>


      <div id="editUserModal" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Add/Edit Food</h5>
              <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form:form method="POST" action="/app/user" modelAttribute="user" enctype="multipart/form-data">
              <div class="modal-body">
                <input id="id" type="hidden" name="id">
                <div class="form-group">
                  <input type="file" name="image" accept="image/*">
                </div>
                <div class="form-group">
                  <input name="name" id="name" placeholder="Name*"
                         type="text" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="nic" id="nic" placeholder="NIC*"
                         type="text" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="epfNo" id="epfNo" placeholder="EPF*"
                         type="text" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="email" id="email" placeholder="Email*"
                         type="email" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="contactNo" id="contactNo" placeholder="Contact No*"
                         type="text" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="address" id="address" placeholder="Address*"
                         type="text" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="userName" id="userName" placeholder="Username*"
                         type="text" class="form-control" data-parsley-required>
                </div>
                <div class="form-group">
                  <input name="password" id="password" placeholder="Password*"
                         type="password" class="form-control">
                </div>
                <div class="form-group">
                  <select id="role" class="selectpicker form-control" name="userRole.id"
                          data-hide-disabled="true" data-live-search="true"
                          title="Role*" data-parsley-required>
                    <option value="1">Admin</option>
                    <option value="2">Accountant</option>
                    <option value="3">Receptionist</option>
                  </select>
                  <br></br>
                  <select id="userStatus" class="selectpicker form-control" name="status.id"
                          data-hide-disabled="true" data-live-search="true"
                          title="Status*" data-parsley-required>
                    <option value="1">Active</option>
                    <option value="2">Inactive</option>
                    <option value="3">Blocked</option>
                    <option value="4">Deleted</option>
                  </select>
                  <br></br>
                  <span class="mandatory-warn">*These fields are mandatory</span>
                </div>
              </div>

              <div class="modal-footer">
                <button id="save" type="button" class="btn btn-primary btn-round btn-sm">Save
                </button>
                <button id="cancel" type="reset" class="btn btn-danger btn-round btn-sm"
                        data-dismiss="modal">Cancel
                </button>
              </div>
            </form:form>
          </div>
        </div>
      </div>

        <%--search menu--%>
      <div id="searchModel" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Search User </h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
                <div class="form-group">
                  <input name="text" placeholder="User Name"
                         type="text"
                         class="form-control">
                </div>
                <div class="form-group">
                  <input name="number" placeholder="NIC"
                         type="number"
                         class="form-control">
                </div>
                <div class="form-group">
                  <input name="number" placeholder="EPF NO"
                         type="number"
                         class="form-control">
                  <br>
                  <select class="selectpicker form-control"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Role">
                    <option>Receptionist</option>
                    <option>Accountant</option>
                    <option>Admin</option>

                  </select>
                  <br></br>
                  <select class="selectpicker form-control"
                          data-hide-disabled="true"
                          data-live-search="true"
                          title="Status">
                    <option>Active</option>
                    <option>Inactive</option>
                  </select>
                </div>
            </div>

            <div class="modal-footer">
              <button type="submit" class="btn btn-primary btn-round btn-sm">
                Search
              </button>
              <button type="button" class="btn btn-danger btn-round btn-sm"
                      data-dismiss="modal">Cancel
              </button>
            </div>

          </div>
        </div>
      </div>
      <!--delete User-->
      <div id="deleteUser" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">Delete User</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <div class="modal-body">
              <input type="hidden" id="delId">
              Are you sure you want to delete the User?
            </div>

            <div class="modal-footer">
              <button id="delete" type="submit" class="btn btn-primary btn-round btn-sm">
                Delete
              </button>
              <button type="button" class="btn btn-danger btn-round btn-sm"
                      data-dismiss="modal">Cancel
              </button>
            </div>
          </div>
        </div>
      </div>

        <%--Report menu--%>
      <div id="reportModal" class="modal fade" tabindex="-1" role="dialog"
           aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-md" role="document">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title">User Reports</h5>
              <button type="button" class="close" data-dismiss="modal"
                      aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
            </div>
            <form:form method="POST" action="/app/user/report" modelAttribute="criteria">
              <div class="modal-body">
                <div class="form-group">
                  <select id="type" name="roleId"
                          class="selectpicker form-control"
                          data-hide-disabled="true"
                          data-live-search="true">
                    <option disabled selected>Select Role</option>
                    <c:forEach items="${roles}" var="role">
                      <option value="${role.value}">${role.text}</option>
                    </c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <select id="status" name="statusId"
                          class="selectpicker form-control"
                          data-hide-disabled="true"
                          data-live-search="true">
                    <option disabled selected>Select Status</option>
                    <c:forEach items="${statuses}" var="status">
                      <option value="${status.value}">${status.text}</option>
                    </c:forEach>
                  </select>
                </div>
              </div>
              <div class="modal-footer">
                <button id="generate" type="submit" class="btn btn-primary btn-round btn-sm">
                  Generate
                </button>
                <button type="button" class="btn btn-danger btn-round btn-sm" data-dismiss="modal">
                  Cancel
                </button>
              </div>
            </form:form>
          </div>
        </div>
      </div>
    </div>
      <jsp:include page="../app/nav.jsp"/>
    <!-- Module JS -->
    <script src="/static/user/js/user.js"></script>
  </body>
  </html>
</compress:html>

