<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!--food search button-->
<button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
        data-target="#menuSearchModal" data-backdrop="static">
  <i class="far fa-search"></i>
  <spring:message code="lbl.common.search"/>
</button>

<!--food report button-->
<button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
        data-target="#food_menuReportModal" data-backdrop="static">
  <i class="far fa-analytics"></i>
  <spring:message code="lbl.common.reports"/>
</button>

<!--add food Menu card-->
<div id="food-menu-container" class="row">
  <div class="col-xl-3 col-md-5 col-lg-5 col-sm-4">
    <div class="card hover-shadow menu-card">
      <div class="add-card card-body d-flex justify-content-between" data-toggle="modal"
           data-target="#menuEditModal" data-backdrop="static">
        <i class="far fa-plus hover-translate-y-n3"></i>
      </div>
    </div>
  </div>

  <div class="col-xl-3 col-md-5 col-lg-5 col-sm-4">
    <div class="card hover-shadow menu-card">
      <div class="card-title">
        Wedding Menu - Silver
      </div>
      <div class="card-body food-container">
        <!--menu foods -->
        <span class="card-subtitle">Rice</span>
        <div class="card hover-shadow menu-food-card">
          <div class="card-body">
            <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder" src="/static/food/img/img_1.png">
            </a>
            <div class="food-detail">
              <span class="food-name">Yangzhou Fried Rice</span>
              <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Rice</span>
              <div>
                <span class="badge badge-primary mt-3">American</span>
                <span class="food-price">Rs.560.00</span>
              </div>
            </div>
            <a href="#" class="action-item text-danger hover-translate-y-n3">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
        <div class="card hover-shadow menu-food-card">
          <div class="card-body">
            <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder" src="/static/food/img/img_2.png">
            </a>
            <div class="food-detail">
              <span class="food-name">Sweet Corn Chicken Soup</span>
              <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Soup</span>
              <div>
                <span class="badge badge-primary mt-3">Japanese</span>
                <span class="food-price">Rs.235.00</span>
              </div>
            </div>
            <a href="#" class="action-item text-danger hover-translate-y-n3">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
        <div class="card hover-shadow menu-food-card">
          <div class="card-body">
            <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder" src="/static/food/img/img_3.png">
            </a>
            <div class="food-detail">
              <span class="food-name">Cheese and Chicken Carbonara</span>
              <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Pasta</span>
              <div>
                <span class="badge badge-primary mt-3">Italian</span>
                <span class="food-price">Rs.650.00</span>
              </div>
            </div>
            <a href="#" class="action-item text-danger hover-translate-y-n3">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
        <div class="card hover-shadow menu-food-card">
          <div class="card-body">
            <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder" src="/static/food/img/img_4.png">
            </a>
            <div class="food-detail">
              <span class="food-name">Crab Rice Noodles</span>
              <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Noodles</span>
              <div>
                <span class="badge badge-primary mt-3">Mongolian</span>
                <span class="food-price">Rs.890.00</span>
              </div>
            </div>
            <a href="#" class="action-item text-danger hover-translate-y-n3">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
        <span class="card-subtitle">Desserts</span>
        <div class="card hover-shadow menu-food-card">
          <div class="card-body">
            <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder" src="/static/food/img/img_7.png">
            </a>
            <div class="food-detail">
              <span class="food-name">Golden Opulence Sundae</span>
              <%--<span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Rice</span>--%>
              <div>
                <span class="badge badge-primary mt-3">American</span>
                <span class="food-price">Rs.800.00</span>
              </div>
            </div>
            <a href="#" class="action-item text-danger hover-translate-y-n3">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
        <div class="card hover-shadow menu-food-card">
          <div class="card-body">
            <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
               data-bw="3px">
              <img alt="Image placeholder" src="/static/food/img/img_6.png">
            </a>
            <div class="food-detail">
              <span class="food-name">Marshmallow creme</span>
              <%--<span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Rice</span>--%>
              <div>
                <span class="badge badge-primary mt-3">American</span>
                <span class="food-price">Rs.460.00</span>
              </div>
            </div>
            <a href="#" class="action-item text-danger hover-translate-y-n3">
              <i class="far fa-trash-alt"></i>
            </a>
          </div>
        </div>
      </div>
      <div class="card-footer">
        <div class="menu-info">
          <span class="badge badge-success mt-3 badge-pill">Available</span>
          <span class="menu-price">Rs.2500.00</span>
        </div>
        <div class="actions d-flex px-3">
          <a href="#" class="action-item text-success hover-translate-y-n3">
            <i class="far fa-print"></i>
          </a>
          <a href="#" class="action-item hover-translate-y-n3" data-toggle="modal"
             data-target="#menuEditModal" data-backdrop="static">
            <i class="far fa-edit"></i>
          </a>
          <a href="#" class="action-item text-danger hover-translate-y-n3">
            <i class="far fa-trash-alt"></i>
          </a>
        </div>
      </div>
    </div>
  </div>
</div>

<p>Food Menu content goes here</p>

<!-- menu edit-->
<div id="menuEditModal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Create/Modify Menu</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <input type="hidden" name="id">
        <div class="form-group">
          <input type="file" name="image" accept="image/*">
        </div>
        <div class="col-lg-6">
          <div class="form-group">
            <input name="text" id="text" placeholder="Name*" type="text"
                   class="form-control">
          </div>
          <div class="form-group">
            <select class="selectpicker form-control" data-hide-disabled="true"
                    data-live-search="true"
                    title="Status*">
              <option>Available</option>
              <option>Unavailable</option>
            </select>
          </div>

          <div class="col-xl-3 col-md-5 col-lg-5 col-sm-4">
            <div class="card hover-shadow menu-card">
              <div class="card-title">
                Food
              </div>
              <div class="card-body food-container">
                <!--menu foods -->
                <span class="card-subtitle">Rice</span>
                <div class="card hover-shadow menu-food-card">
                  <div class="card-body">
                    <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
                       data-bw="3px">
                      <img alt="Image placeholder" src="/static/food/img/img_1.png">
                    </a>
                    <div class="food-detail">
                      <span class="food-name">Yangzhou Fried Rice</span>
                      <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Rice</span>
                      <div>
                        <span class="badge badge-primary mt-3">American</span>
                        <span class="food-price">Rs.560.00</span>
                      </div>
                    </div>
                    <a href="#" class="action-item text-danger hover-translate-y-n3">
                      <i class="far fa-trash-alt"></i>
                    </a>
                  </div>
                </div>
                <div class="card hover-shadow menu-food-card">
                  <div class="card-body">
                    <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
                       data-bw="3px">
                      <img alt="Image placeholder" src="/static/food/img/img_2.png">
                    </a>
                    <div class="food-detail">
                      <span class="food-name">Sweet Corn Chicken Soup</span>
                      <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Soup</span>
                      <div>
                        <span class="badge badge-primary mt-3">Japanese</span>
                        <span class="food-price">Rs.235.00</span>
                      </div>
                    </div>
                    <a href="#" class="action-item text-danger hover-translate-y-n3">
                      <i class="far fa-trash-alt"></i>
                    </a>
                  </div>
                </div>
                <div class="card hover-shadow menu-food-card">
                  <div class="card-body">
                    <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
                       data-bw="3px">
                      <img alt="Image placeholder" src="/static/food/img/img_3.png">
                    </a>
                    <div class="food-detail">
                      <span class="food-name">Cheese and Chicken Carbonara</span>
                      <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Pasta</span>
                      <div>
                        <span class="badge badge-primary mt-3">Italian</span>
                        <span class="food-price">Rs.650.00</span>
                      </div>
                    </div>
                    <a href="#" class="action-item text-danger hover-translate-y-n3">
                      <i class="far fa-trash-alt"></i>
                    </a>
                  </div>
                </div>
                <div class="card hover-shadow menu-food-card">
                  <div class="card-body">
                    <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
                       data-bw="3px">
                      <img alt="Image placeholder" src="/static/food/img/img_4.png">
                    </a>
                    <div class="food-detail">
                      <span class="food-name">Crab Rice Noodles</span>
                      <span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Noodles</span>
                      <div>
                        <span class="badge badge-primary mt-3">Mongolian</span>
                        <span class="food-price">Rs.890.00</span>
                      </div>
                    </div>
                    <a href="#" class="action-item text-danger hover-translate-y-n3">
                      <i class="far fa-trash-alt"></i>
                    </a>
                  </div>
                </div>
                <span class="card-subtitle">Desserts</span>
                <div class="card hover-shadow menu-food-card">
                  <div class="card-body">
                    <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
                       data-bw="3px">
                      <img alt="Image placeholder" src="/static/food/img/img_7.png">
                    </a>
                    <div class="food-detail">
                      <span class="food-name">Golden Opulence Sundae</span>
                      <%--<span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Rice</span>--%>
                      <div>
                        <span class="badge badge-primary mt-3">American</span>
                        <span class="food-price">Rs.800.00</span>
                      </div>
                    </div>
                    <a href="#" class="action-item text-danger hover-translate-y-n3">
                      <i class="far fa-trash-alt"></i>
                    </a>
                  </div>
                </div>
                <div class="card hover-shadow menu-food-card">
                  <div class="card-body">
                    <a class="avatar avatar-xs border-secondary-light border-w2 shadow-lg"
                       data-bw="3px">
                      <img alt="Image placeholder" src="/static/food/img/img_6.png">
                    </a>
                    <div class="food-detail">
                      <span class="food-name">Marshmallow creme</span>
                      <%--<span class="badge badge-success mt-3 d-il-block badge-pill badge-sm">Rice</span>--%>
                      <div>
                        <span class="badge badge-primary mt-3">American</span>
                        <span class="food-price">Rs.460.00</span>
                      </div>
                    </div>
                    <a href="#" class="action-item text-danger hover-translate-y-n3">
                      <i class="far fa-trash-alt"></i>
                    </a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <input name="number" id="number"
                   type="number" class="form-control" placeholder="Additional Chargers*">
          </div>
          *These fields are mandatory
        </div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-primary btn-round btn-sm">Save
        </button>
        <button type="button" class="btn btn-danger btn-round btn-sm"
                data-dismiss="modal">Cancel
        </button>
      </div>


    </div>
  </div>
</div>

<!-- menu report-->
<div id="food_menuReportModal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Food Menu Reports</h5>
        <button type="button" class="close" data-dismiss="modal"
                aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <div class="col-lg-6">
          <div class="form-group">
            From
            <input name="text" placeholder="From" type="date"
                   class="form-control">
          </div>
          To
          <div class="form-group">
            <input name="text" placeholder="To" type="date"
                   class="form-control">
            <br>
            <div class="form-group">
              <select name="category.id" class="selectpicker form-control" data-hide-disabled="true"
                      data-live-search="true" title="Catergory*">
                <option value="1">Rice</option>
                <option value="2">Noodles</option>
                <option value="3">Pasta</option>
              </select>
              <br></br>
              <select name="cuisine.id" class="selectpicker form-control" data-hide-disabled="true"
                      data-live-search="true" title="Cuisine*">
                <option value="1">American</option>
                <option value="2">Chinese</option>
                <option value="3">Italian</option>
                <option value="4">Japanese</option>
                <option value="5">Add new....</option>
              </select>
              <br></br>
              <span class="mandatory-warn">*These fields are mandatory</span>
            </div>
          </div>
        </div>
      </div>

      <div class="modal-footer">
        <button type="submit" class="btn btn-primary btn-round btn-sm">
          Generate the Report
        </button>
        <button type="button" class="btn btn-danger btn-round btn-sm"
                data-dismiss="modal">Cancel
        </button>
      </div>

    </div>
  </div>
</div>

<!--menu search-->
<div id="menuSearchModal" class="modal fade" tabindex="-1" role="dialog"
     aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-md" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Search Menu</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <br class="col-lg-6">
        <div class="form-group">
          <input name="text" placeholder="Menu Name" type="text"
                 class="form-control">
        </div>
        <br></br>
        <div class="form-group">
          <input name="text" placeholder="Food Name" type="text"
                 class="form-control">
        </div>
        <div class="form-group">
          <select class="selectpicker form-control" data-hide-disabled="true"
                  data-live-search="true"
                  title="Status">
            <option>Available</option>
            <option>Unavailable</option>
          </select>
        </div>
      </div>

    <div class="modal-footer">
      <button type="submit" class="btn btn-primary btn-round btn-sm">Search
      </button>
      <button type="button" class="btn btn-danger btn-round btn-sm"
              data-dismiss="modal">Cancel
      </button>
    </div>
    </div>
  </div>

  <%--Success button--%>
  <div class="alert alert-success alert-dismissible fade show" role="alert">
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
    </button>
    <i class="icon far fa-check"></i>
    <div class="content">
      <strong>Successfully Saved</strong> The data has being saved successfully.
    </div>
  </div>
  <script type="text/javascript">
      $(function () {
          $('.selectpicker').selectpicker();
      });
  </script>
</div>